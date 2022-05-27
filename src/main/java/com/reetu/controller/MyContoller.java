package com.reetu.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.reetu.beans.Cloth;

@Controller
public class MyContoller {
	
	              RestTemplate rt = new RestTemplate();
	              
	              String URL = "http://localhost:2555";
	              
	              @RequestMapping("/")
	              public String home() {
	            	  return "index";
	              }
	              
	              //to view all clothes
	              @GetMapping("/allclothes")
	              public String getallcloth(Model model){
	            	  String API = "/getallclothes";
	            	  List<Cloth> cl = rt.getForObject(URL+API,List.class);
	            	  model.addAttribute("allClothess",cl);
	            	  return "Viewsallclothes";
	            	  
	              }
	              
	              //to add a clothes
	              @RequestMapping("/AddClothes")
	              public String addclothes(@ModelAttribute Cloth c,@RequestPart("image") MultipartFile image, Model model) {
	            	  String API = "/addcloth";
	            	  
	            	HttpHeaders header = new HttpHeaders();
	            	header.setContentType(MediaType.MULTIPART_FORM_DATA);
	            	
	          		LinkedMultiValueMap<String, Object> b = new LinkedMultiValueMap<>();
	          		b.add("image", convert(image));
	          		b.add("Cloth",c);
	          		
	          		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity=new HttpEntity<>(b,header);
	          		
	          		ResponseEntity<String> result=rt.postForEntity(URL+API,requestEntity, String.class);
	          		if(result.getStatusCode()==HttpStatus.OK){
	          			model.addAttribute("addresult", c.getName()+"Added Sussesfully!");
	          		}else {
	          			model.addAttribute("addresult", c.getName()+"Exists Already!");
	          		}
	          		 return "index";
	            	  
	              }
	              
	              //code for converting multipart file into filesystemresources
	              public static FileSystemResource convert(MultipartFile file){
	            	File convFile=new File(file.getOriginalFilename());
	            	try {
	            		convFile.createNewFile();
	            		FileOutputStream fos=new FileOutputStream(convFile);
	            		fos.write(file.getBytes());
	            		fos.close();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
	            return new FileSystemResource(convFile);

	           	  }
	              
	              @RequestMapping("/delete")
	              public String delete(Model model, int cid) {
	            	  String API="/delete/"+cid;
	            	  ResponseEntity<String> delete = rt.exchange(URL+API,HttpMethod.DELETE,null,String.class);
	            	  String ok = delete.getBody();
	            	  model.addAttribute("OK",ok);
	            	  return "index";
	            	  
	              }
	              
	              @ModelAttribute
	              public void getallids(Model model){
	            	  String API = "/getallids";
	            	  List<Integer> ids = rt.getForObject(URL+API, List.class);
	            	  model.addAttribute("ids",ids);
	              }
	              
	              @RequestMapping("/updatepage")
	              public String forwardpage() {
	            	  return "updatecloth";
	              }
	              @RequestMapping("/update")
	              public String update(@ModelAttribute Cloth c, Model model) {
	            	  String API="/update";
	            	  HttpEntity<Cloth> requestEntity = new HttpEntity<>(c);
	            	  ResponseEntity<String> upd = rt.exchange(URL+API, HttpMethod.PUT,requestEntity, String.class);
	            	  String str=upd.getBody();
	            	  model.addAttribute("update" ,str);
	            	  return "updatecloth";
	              }
	              
	              @RequestMapping("/likename")
	              public String forwared() {
	            	  return "samenamecloth";
	              }
	              
	              @RequestMapping("/view")
	              public String likenamecloth(String name, Model model) {
	            	  String API="/getcolthlikename/"+name;
	            	  List<Cloth> C = rt.getForObject(URL+API, List.class);
	            	  model.addAttribute("Clothes",C);
	            	  return "samenamecloth";
	              }
	              
	              @RequestMapping("/getimage")
	              public void getphoto(int cid, HttpServletResponse response) {
	            	  String API="/getImage/"+cid;
	            	  
	            	  try {
	            		  byte [] b=rt.getForObject(URL+API, byte[].class);
	            		  response.getOutputStream().write(b);
					} catch (Exception e) {
						e.printStackTrace();
					}
	            	  
	            	  
	              }
	              
	              @RequestMapping("/updateimage")
	              public String updatephoto(int cid,@RequestPart("image") MultipartFile image, Model model){
	            	  String API="/updateImage";
	            	  
		            	HttpHeaders header = new HttpHeaders();
		            	header.setContentType(MediaType.MULTIPART_FORM_DATA);
		            	
		            	LinkedMultiValueMap<String, Object> data= new LinkedMultiValueMap<>();
		            	data.add("cid", cid);
		            	data.add("image", convert(image));
		            	
		            	HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(data, header);
		            	ResponseEntity<String> result= rt.exchange(URL+API,HttpMethod.PUT, requestEntity, String.class);
		            	//String st=result.getBody();
		            	if(result.getStatusCode()==HttpStatus.OK) {
		            		 model.addAttribute("getresult",  "Updateded Successfully!");
		            	}else {
		            		model.addAttribute("getresult", "Something Went Wrong!");
		            	}
		            	
		            	  API = "/getallclothes";
		            	  List<Cloth> cl = rt.getForObject(URL+API,List.class);
		            	  model.addAttribute("allClothess",cl);
		            	  return "Viewsallclothes";	
	              }
	              

}
