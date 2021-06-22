package com.chengchw.SHEA.Controller;

import java.util.Date;
import java.util.List;
import java.util.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chengchw.SHEA.Model.Attribute;
import com.chengchw.SHEA.Model.Guest;
import com.chengchw.SHEA.Model.Product;
import com.chengchw.SHEA.Model.Provider;
import com.chengchw.SHEA.Service.GuestService;
import com.chengchw.SHEA.Service.ProductService;
import com.chengchw.SHEA.Service.ProviderService;
import com.chengchw.SHEA.Validator.GuestValidator;
import com.chengchw.SHEA.Validator.ProviderValidator;

@Controller 
public class HomeController {
	
	private final ProductService prodSer;
	private final GuestService gueSer;
	private final ProviderService provSer;
	private final ProviderValidator proVal;
	private final GuestValidator gueVal;
	private Attribute attr = new Attribute();
	
	
	
	public HomeController(ProductService prodSer,GuestService gueSer,ProviderService provSer, 
						  ProviderValidator proVal,GuestValidator gueVal) {
		
		this.gueSer = gueSer;
		this.prodSer = prodSer;
		this.provSer = provSer;
		this.gueVal = gueVal;
		this.proVal = proVal;
		
		
	}
	
	
	@RequestMapping("")
	public String welcomePage(Model model) {
		
		List<Product> allProd = this.prodSer.getAllProd();
		model.addAttribute("displayProduct", allProd);
		
		return "dashboard.jsp";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("theproviderid");
		return "redirect:/";
	}
	
//Provider
	
	@RequestMapping("/provider")
	public String providerLoginRegister(@ModelAttribute("newprovider") Provider newprovider, Model model, HttpSession session) {
		//Attribute attr = new Attribute();
		//System.out.println(attr.States);
		session.removeAttribute("theproviderid");
		model.addAttribute("states",this.attr.States);
		
		
		return "provider_loging.jsp";
		
	}
	
	
	@PostMapping("/registprovider")
	public String registerProvider(@Valid @ModelAttribute("newprovider") Provider newprovider,BindingResult result,
								   RedirectAttributes redirectAttributes, HttpSession session, Model model) {
		
		this.proVal.validate(newprovider, result);
		//System.out.println("apple");
		if(result.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("regerror", "Email already existed or password no match");
			
			model.addAttribute("states",this.attr.States);
			
			return "provider_loging.jsp";
		}
		
		else {
			
			Provider newProv = this.provSer.registerProvider(newprovider);
			Long newProvId = newProv.getId();
			session.setAttribute("theproviderid", newProvId);
//			Provider theProv = this.provSer.findProvById(newProvId);
//			model.addAttribute("theprovider", theProv);
			//return "provider_product_page.jsp";
			return "redirect:/loginproviderpage";
			
		}
		
		
	}
	
	@PostMapping("/loginprovider")
	public String providerlogin(@RequestParam(value = "inputemail") String inputemail,
								@RequestParam(value = "inputpassword") String inputpass,
								HttpSession session,RedirectAttributes redirectAttributes) {
		
		if(this.provSer.authenticateUser(inputemail, inputpass)) {
			
			//session.removeAttribute("theprovider");
			Provider theprovider = this.provSer.findProvByEmail(inputemail);
			Long theProvId = theprovider.getId();
			session.setAttribute("theproviderid", theProvId);
			
			return "redirect:/loginproviderpage";
			//return "provider_product_page.jsp";
		}
		
		else {
			
			redirectAttributes.addFlashAttribute("logerror", "Please enter valid email or password");
			return "redirect:/provider";
		}
	}
	
	@RequestMapping("/loginproviderpage")
	public String providerloginPage(HttpSession session , Model model) {
		
	
		if (session.getAttribute("theproviderid") != null) {
			
			Long theProvId = (Long)session.getAttribute("theproviderid");
			Provider theProv = this.provSer.findProvById(theProvId);
			model.addAttribute("theprovider", theProv);
			//List <Product> prodOnShelf = theProv.getOnShelfProducts();
			//Product tryprod = prodOnShelf.get(0);
			

			return "provider_product_page.jsp";
		}
		
		else {
			
			return "redirect:/provider";
		}
		
		
	}
	
	@RequestMapping("/onshelfprod")
	public String onshelfProdPage(@ModelAttribute("newproduct") Product newProduct, Model model, HttpSession session) {
		//System.out.println(session.getAttribute("theproviderid"));
		
		if (session.getAttribute("theproviderid") == null) {
			
			return "redirect:/provider";
		}
		model.addAttribute("allbrand",this.attr.Brands);
		model.addAttribute("madeyear",this.attr.Year);
		model.addAttribute("damagerate",this.attr.DamageRate);
		model.addAttribute("states",this.attr.States);
		
		return "sell_product_page.jsp";
	}
	
	
	@PostMapping("/onshelf")
	public String uploadProduct(@Valid @ModelAttribute("newproduct") Product newProduct, BindingResult result, 
								RedirectAttributes redirectAttributes, Model model, HttpSession session) {
		
		//System.out.println("In");
		Date currentDate = new Date();
		
		if(newProduct.getExpireDate() == null ||newProduct.getExpireDate().before(currentDate) || result.hasErrors()) {
			
			
			model.addAttribute("showerrors", "Please put validate expiration date");
			model.addAttribute("allbrand",this.attr.Brands);
			model.addAttribute("madeyear",this.attr.Year);
			model.addAttribute("damagerate",this.attr.DamageRate);
			model.addAttribute("states",this.attr.States);
			System.out.println("Bad Product");
			return "sell_product_page.jsp";
			//return "redirect:/onshelfprod";
			
		}
		
		else {
			
			//System.out.println("Good Product");
			Long theProviderId = (Long)session.getAttribute("theproviderid");
			Provider theProvider = this.provSer.findProvById(theProviderId);
			newProduct.setOwner(theProvider);
			newProduct.setCurrentPrice(newProduct.getIniPrice());
			newProduct.addToHistoryPrice(newProduct.getIniPrice());
			//System.out.println(newProduct.getOwner());
			this.prodSer.registerProd(newProduct);
			return "redirect:/loginproviderpage";
		}
		
	}
	
	@GetMapping("/edit/{providerid}/{productid}")
	public String editproductpage(@PathVariable("providerid") Long provId, @PathVariable("productid") Long prodId, HttpSession session,
								   Model model) {

		
		Long theprovid = (Long)session.getAttribute("theproviderid");
//		System.out.println(theprovid.equals(provId));
		if(!theprovid.equals(provId)) {
		
			return "redirect:/loginproviderpage";
		}
		
		Product editprod = this.prodSer.findProdById(prodId);
		model.addAttribute("editprod",editprod);
		model.addAttribute("allbrand",this.attr.Brands);
		model.addAttribute("madeyear",this.attr.Year);
		model.addAttribute("damagerate",this.attr.DamageRate);
		model.addAttribute("states",this.attr.States);
		List<Guest> bidderlist = new ArrayList<Guest>(editprod.getGuestPriceMapping().keySet());
		model.addAttribute("bidderlist",bidderlist);
		//model.addAttribute("biddermap",bidderlist);
	
		return "product_edit_page.jsp";
	}
	
	@PostMapping("/editproduct")
	public String editproduct(@Valid @ModelAttribute("editprod") Product editprod, BindingResult result, Model model){
		
		
		Date currentDate = new Date();
		
		if(result.hasErrors()) {
			
			System.out.println(editprod.getGuestPriceMapping());
			return "product_edit_page.jsp";
		}
		
		
		if(editprod.getExpireDate() == null ||editprod.getExpireDate().before(currentDate) || result.hasErrors()) {
			System.out.println("Bad Input");
			model.addAttribute("showerrors", "Please put validate expiration date");
			model.addAttribute("allbrand",this.attr.Brands);
			model.addAttribute("madeyear",this.attr.Year);
			model.addAttribute("damagerate",this.attr.DamageRate);
			model.addAttribute("states",this.attr.States);
			
			return "product_edit_page.jsp";
		}
		
		Product oldProd = this.prodSer.findProdById(editprod.getId());
		editprod.setGuestPriceMapping(oldProd.getGuestPriceMapping());
		this.prodSer.registerProd(editprod);
		
		
		
		return "redirect:/loginproviderpage";
	}
	
	@PostMapping("/delete/{editprodid}")
	public String deleteproduct(@PathVariable("editprodid") Long prodId) {
		
		Product deleprod = this.prodSer.findProdById(prodId);
		this.prodSer.removeProd(deleprod);
		return "redirect:/loginproviderpage";
	}
		
		
		
		
	
//Guest Part
	
	@RequestMapping("/guest")
	public String providerLoginRegister(@ModelAttribute("newguest") Guest newpguest, HttpSession session) {
		session.removeAttribute("theguestid");
		return "guest_loging.jsp";
		
	}
	
	@PostMapping("/registguest")
	public String registerGuest(@Valid @ModelAttribute("newguest") Guest newguest,BindingResult result,
								   RedirectAttributes redirectAttributes, HttpSession session, Model model) {
		
		this.gueVal.validate(newguest, result);
		//System.out.println("apple");
		if(result.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("regerror", "Email already existed or password not match");
			
			
			
			return "guest_loging.jsp";
		}
		
		else {
			//System.out.println("yes guest");
			Guest newGuest = this.gueSer.registerGuest(newguest);
			Long newGuestId = newGuest.getId();
			session.setAttribute("theguestid", newGuestId);
			
			

			return "redirect:/loginguestpage";
			
		}
		
		
	}
	@PostMapping("/loginguest")
	public String guestlogin(@RequestParam(value = "inputemail") String inputemail,
			@RequestParam(value = "inputpassword") String inputpass,
			HttpSession session,RedirectAttributes redirectAttributes) {

		if(this.gueSer.authenticateUser(inputemail, inputpass)) {
		
		//session.removeAttribute("theprovider");
			Guest theguest = this.gueSer.findGuestByEmail(inputemail);
			Long theGueId = theguest.getId();
			session.setAttribute("theguestid", theGueId);
		
			return "redirect:/loginguestpage";
		//return "provider_product_page.jsp";
		}
		
		else {
		
			redirectAttributes.addFlashAttribute("logerror", "Please enter valid email or password");
			return "redirect:/guest";
		}
}
	
	
	
	
	@RequestMapping("/loginguestpage")
	public String guestloginPage(HttpSession session , Model model) {
		
	
		if (session.getAttribute("theguestid") != null) {
			
			Long theGuestId = (Long)session.getAttribute("theguestid");
			Guest theGuest = this.gueSer.findGuestById(theGuestId);
			model.addAttribute("theguest", theGuest);
			
			//List<Product> allProd = this.prodSer.getAllProd();
			List<Product> allProdbidded = this.prodSer.findcurrentbidprod(theGuestId);
			List<Product> allProdNotNidded = this.prodSer.findcurrentbidprodNot(theGuestId);
			model.addAttribute("displayProductBid", allProdbidded);
			model.addAttribute("displayProductBidNot", allProdNotNidded);
			
			//List <Product> prodOnShelf = theProv.getOnShelfProducts();
			//Product tryprod = prodOnShelf.get(0);
			

			return "guest_product_page.jsp";
		}
		
		else {
			
			return "redirect:/guest";
		}	
	}
	
	@GetMapping("/proddetail/{productid}")
	public String viewproductdetail(@PathVariable("productid") Long prodId, Model model, HttpSession session) {
		
		Product viewedProd = this.prodSer.findProdById(prodId);
		model.addAttribute("theproduct",viewedProd);
		Long theGuestId = (Long)session.getAttribute("theguestid");
		Guest theGuest = this.gueSer.findGuestById(theGuestId);
		model.addAttribute("theGuest",theGuest );
		
		return "product_detail_page.jsp";
		
		
	}
	
	@RequestMapping("/bid/{theGuestid}/{theproductid}")
	public String bidproductpage(@PathVariable("theGuestid") Long guestId, @PathVariable("theproductid") Long prodId,HttpSession session, Model model) {
		
		Long currGuesId = (Long)session.getAttribute("theguestid");
		System.out.println(guestId);
		if (currGuesId.equals(guestId)) {
			
			Product biddingProd = this.prodSer.findProdById(prodId);
			model.addAttribute("theproduct",biddingProd);
			return "bidding_product_page.jsp";
		}
		
		else {
			
			return "redirect:/loginguestpage";
		}
		
	}
	
	@PostMapping("/makebid/{theproductid}")
	public String bidproduct(@PathVariable("theproductid") Long prodId, @RequestParam(value = "bidprice" ) int bidprice, Model model,
							RedirectAttributes redirectAttributes, HttpSession session) {
		Product theProd = this.prodSer.findProdById(prodId);
		int minibid = theProd.getBidPrice();
		Long guestid = (Long) session.getAttribute("theguestid");
		Guest theguest = this.gueSer.findGuestById(guestid);
		
		
		if (bidprice - theProd.getCurrentPrice() < minibid) {
			
			redirectAttributes.addFlashAttribute("errormess", "Bidding price should larger than current price plus minimum bid ");
			
			String redirectpage = "redirect:/bid/"+guestid+"/"+prodId;
			
			return redirectpage;
		}
		
		theProd.setCurrentPrice(bidprice);
		theProd.addToHistoryPrice(bidprice);
		theProd.addBidders(theguest);
		theProd.setCurrentBidId(guestid);
		theProd.updateGuestPrice(theguest, bidprice);
		System.out.println(theProd.getHistoryPrice());
		this.prodSer.registerProd(theProd);
		
		return "redirect:/loginguestpage";
	}
	
	
	
	
	
	

}
