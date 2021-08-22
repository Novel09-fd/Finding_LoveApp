package com.novel.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.novel.config.JwtTokenUtil;
import com.novel.entity.DataUser;
import com.novel.service.DataUserService;
import com.novel.service.MyUserDetails;
import com.novel.utility.FileUtility;



@RestController
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil tokenUtil;

	@Autowired
	private MyUserDetails uService;
	
	@Autowired
	private DataUserService duService;

	@GetMapping("/getalluser")
	public ResponseEntity<?> getAllUser() {
		return ResponseEntity.ok(duService.getAllUser());
	}
	
//	@GetMapping("/getalluser/female")
//	public ResponseEntity<?> getUsersexFemale(@RequestParam("usersex")String usersex){
//		return ResponseEntity.ok(duService.getUsersexFemale(usersex));
//	}
//	
	@GetMapping("user/{usersex}")
	public List<DataUser> getByUsersex(@PathVariable String usersex){
		return duService.getUsersex(usersex);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody DataUser dataUser) throws Exception{
		authenticate(dataUser.getUsername(), dataUser.getPassword());
		
		final UserDetails userDetails = uService.loadUserByUsername(dataUser.getUsername());
		final String token = tokenUtil.generateToken(userDetails, duService.getUserByUsername(dataUser.getUsername()).getUsersex());

		return ResponseEntity.ok(token);
	}
	
	
	@PostMapping("/register")
	public String saveUser(@RequestParam(value = "file") MultipartFile photo , @ModelAttribute (value = "data")String dataJSON) throws IOException {
		
		String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
		
		String uploadDir  = "src/main/java/user-photo";
		FileUtility.saveFile(uploadDir, fileName, photo);
		DataUser user = new Gson().fromJson(dataJSON, DataUser.class);
		
		
		BCryptPasswordEncoder passwordEncode = new BCryptPasswordEncoder();
		user.setPassword(passwordEncode.encode(user.getPassword()));
		
		user.setPhoto(fileName);
		this.duService.saveUser(user);
		return "Registration success";
	}
	
	@GetMapping(value="/user/image/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(@PathVariable String name) throws IOException {
		final InputStream in = getClass().getResourceAsStream("/user-photos/"+name);
		return IOUtils.toByteArray(in);
	}
	
	private void authenticate(String username, String password) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			// TODO: handle exception
			throw new Exception("USER_DISABLED",e);
		} catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new Exception("INVALID_CREDENTIALS",e);
		}
	}
	
}
