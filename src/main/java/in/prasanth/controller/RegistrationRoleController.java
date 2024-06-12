package in.prasanth.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.prasanth.dao.Product;
import in.prasanth.entity.AuthenticationRequest;
import in.prasanth.entity.AuthenticationResponse;
import in.prasanth.entity.Role;
import in.prasanth.entity.UserTbl;
import in.prasanth.security.MyUserDetailsService;
import in.prasanth.service.RoleService;
import in.prasanth.service.UserService;
import in.prasanth.util.JwtUtil;

@CrossOrigin
@RestController
public class RegistrationRoleController {
	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@PostMapping("/createNewRole")
	public Role createNewRole(@RequestBody Role role) {
		Role rolerecord = roleService.createNewRole(role);
		return rolerecord;
	}

	@PostMapping("/registerNewUser")
	public UserTbl registerNewUser(@RequestBody UserTbl user) {
		UserTbl userrecord = userService.registerNewUser(user);
		return userrecord;
	}

	@GetMapping("/addData")
	public UserTbl addData() {
		UserTbl addData = userService.addData();
		return addData;
	}

	@PostMapping("/addUser")
	public UserTbl addUser() {
		UserTbl addUser = userService.addUser();
		return addUser;
	}

	// @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/searchProduct/{query}")
	// @GetMapping("/searchProduct")
	@PreAuthorize("hasAuthority('ROLE_Admin')")
	public Product searchProducts(@PathVariable(name = "query") String query) {
		// public List<Product> searchProducts() {
		Map<String, Product> m = new HashMap<>();
		List<Product> listOfProducts = new ArrayList<>();
		Product p1 = new Product();
		p1.setName("Mango");
		p1.setPrice(120.0);
		p1.setImageUrl(
				"https://up.yimg.com/ib/th?id=OIP.xqzVK3m_GpIC8rDeM91bbwHaEc&%3Bpid=Api&rs=1&c=1&qlt=95&w=176&h=105");
		Product p2 = new Product();
		p2.setName("Apple");
		p2.setPrice(100.0);
		p2.setImageUrl(
				"https://up.yimg.com/ib/th?id=OIP.SEMRDAqH9pGGt62J5kEA7gHaFl&pid=Api&rs=1&c=1&qlt=95&w=165&h=124");
		listOfProducts.add(p1);
		listOfProducts.add(p2);
		m.put("mango", p1);
		m.put("apple", p2);
		return m.get(query);

	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@GetMapping("/forAdmin")
	// @PreAuthorize("hasAuthority('ROLE_Admin') and hasAuthority('ROLE_User')")
	// @PreAuthorize("hasAuthority('ROLE_Admin')")
	public UserTbl forAdmin() {
		UserTbl adminData = userService.forAdmin();
		return adminData;
	}

	@GetMapping("/forUser")
	@PreAuthorize("hasAuthority('ROLE_User')")
	public UserTbl forUser() {
		UserTbl userData = userService.forUser();
		return userData;
	}

	@GetMapping("/getAllItems")
	public List<Product> getAllItems() {
		List<Product> listOfProducts = new ArrayList<>();
		Product p1 = new Product();
		p1.setName("Mango");
		p1.setPrice(120.0);
		p1.setImageUrl(
				"https://up.yimg.com/ib/th?id=OIP.xqzVK3m_GpIC8rDeM91bbwHaEc&%3Bpid=Api&rs=1&c=1&qlt=95&w=176&h=105");
		Product p2 = new Product();
		p2.setName("Apple");
		p2.setPrice(100.0);
		p2.setImageUrl(
				"https://up.yimg.com/ib/th?id=OIP.SEMRDAqH9pGGt62J5kEA7gHaFl&pid=Api&rs=1&c=1&qlt=95&w=165&h=124");
		Product p3 = new Product();
		p3.setName("Mango");
		p3.setPrice(120.0);
		p3.setImageUrl(
				"https://up.yimg.com/ib/th?id=OIP.xqzVK3m_GpIC8rDeM91bbwHaEc&%3Bpid=Api&rs=1&c=1&qlt=95&w=176&h=105");
		Product p4 = new Product();
		p4.setName("Apple");
		p4.setPrice(100.0);
		p4.setImageUrl(
				"https://up.yimg.com/ib/th?id=OIP.SEMRDAqH9pGGt62J5kEA7gHaFl&pid=Api&rs=1&c=1&qlt=95&w=165&h=124");
		Product p5 = new Product();
		p5.setName("Mango");
		p5.setPrice(120.0);
		p5.setImageUrl(
				"https://up.yimg.com/ib/th?id=OIP.xqzVK3m_GpIC8rDeM91bbwHaEc&%3Bpid=Api&rs=1&c=1&qlt=95&w=176&h=105");
		Product p6 = new Product();
		p6.setName("Apple");
		p6.setPrice(100.0);
		p6.setImageUrl(
				"https://up.yimg.com/ib/th?id=OIP.SEMRDAqH9pGGt62J5kEA7gHaFl&pid=Api&rs=1&c=1&qlt=95&w=165&h=124");
		Product p7 = new Product();
		p7.setName("Mango");
		p7.setPrice(120.0);
		p7.setImageUrl(
				"https://up.yimg.com/ib/th?id=OIP.xqzVK3m_GpIC8rDeM91bbwHaEc&%3Bpid=Api&rs=1&c=1&qlt=95&w=176&h=105");
		Product p8 = new Product();
		p8.setName("Apple");
		p8.setPrice(100.0);
		p8.setImageUrl(
				"https://up.yimg.com/ib/th?id=OIP.SEMRDAqH9pGGt62J5kEA7gHaFl&pid=Api&rs=1&c=1&qlt=95&w=165&h=124");
		listOfProducts.add(p1);
		listOfProducts.add(p2);
		listOfProducts.add(p3);
		listOfProducts.add(p4);
		listOfProducts.add(p5);
		listOfProducts.add(p6);
		listOfProducts.add(p7);
		listOfProducts.add(p8);
		return listOfProducts;

	}

	@GetMapping("/getItemData/{query}")
	public Product getItem(@PathVariable(name = "query") String query) {
		// public List<Product> searchProducts() {
		Map<String, Product> m = new HashMap<>();
		List<Product> listOfProducts = new ArrayList<>();
		Product p1 = new Product();
		p1.setName("Mango");
		p1.setPrice(120.0);
		p1.setImageUrl(
				"https://up.yimg.com/ib/th?id=OIP.xqzVK3m_GpIC8rDeM91bbwHaEc&%3Bpid=Api&rs=1&c=1&qlt=95&w=176&h=105");
		Product p2 = new Product();
		p2.setName("Apple");
		p2.setPrice(100.0);
		p2.setImageUrl(
				"https://up.yimg.com/ib/th?id=OIP.SEMRDAqH9pGGt62J5kEA7gHaFl&pid=Api&rs=1&c=1&qlt=95&w=165&h=124");
		listOfProducts.add(p1);
		listOfProducts.add(p2);
		m.put("mango", p1);
		m.put("apple", p2);
		return m.get(query);

	}

}
