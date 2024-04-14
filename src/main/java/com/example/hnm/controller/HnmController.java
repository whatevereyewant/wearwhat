package com.example.hnm.controller;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.net.URLEncoder;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.hnm.config.auth.PrincipalDetails;
import com.example.hnm.entity.CustomerEntity;
import com.example.hnm.dto.*;
import com.example.hnm.service.CustomerService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class HnmController {
    
    @Autowired
    private CustomerService customerService;

    // 홈페이지 //
    @GetMapping("/")
    public String homepage(Authentication authentication, Model model) {
        if(authentication != null){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("username", userDetails.getUsername());
        }
        return "homepage";
    }

    // 회원가입 //
    @GetMapping("/membership")
    public String membershipPage() {
        return "membership";
    }
    @PostMapping("/membership")
    public String join(@ModelAttribute CustomerEntity dto) {

        customerService.joinCustomerDto(dto);
        return "redirect:/loginpage";
    }
    // 로그인 페이지 //
    @GetMapping("/loginpage")
    public String loginpage(@RequestParam(value = "errorMessage", required = false) String errorMessage, Model model) {
        
        model.addAttribute("errorMessage", errorMessage);
        return "login";
        }
    
    // 유저 홈페이지 //
    @GetMapping("/userhome")
    public String userhome(Authentication authentication, Model model) {
        PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        CustomerEntity customer = customerService.getCustomerInfo(userDetails.getUsername());
        //model.addAttribute("username", userDetails.getUsername());
                // 모델에 사용자 정보 추가
        model.addAttribute("username", customer.getUsername());
        model.addAttribute("usernickname", customer.getUsernickname());
        return "userhome";
    }

    @GetMapping("/mypage")
    public String mypage(Authentication authentication, Model model){
        PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        CustomerEntity customer = customerService.getCustomerInfo(userDetails.getUsername());
        //model.addAttribute("username", userDetails.getUsername());
                // 모델에 사용자 정보 추가
        model.addAttribute("username", customer.getUsername());
        model.addAttribute("realname", customer.getRealname());
        model.addAttribute("userheight", customer.getUserheight());
        model.addAttribute("userweight", customer.getUserweight());
        model.addAttribute("usernickname", customer.getUsernickname());
        model.addAttribute("userphonenum", customer.getUserphonenum());

        return "mypage";
    }
    

    //마이페이지 수정폼
    @GetMapping("/mypage/update")
    public String myupdate(Authentication authentication, Model model){
        PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        CustomerEntity customer = customerService.getCustomerInfo(userDetails.getUsername());
        model.addAttribute("username", customer.getUsername());
        model.addAttribute("realname", customer.getRealname());
        model.addAttribute("userheight", customer.getUserheight());
        model.addAttribute("userweight", customer.getUserweight());
        model.addAttribute("usernickname", customer.getUsernickname());
        model.addAttribute("userphonenum", customer.getUserphonenum());
        return "myupdate";
    }
    
    @PostMapping("/mypage/update")
    public ResponseEntity<?> updateCustomer(@ModelAttribute CustomerupDto updateDto, Authentication authentication) {
        PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        customerService.updateCustomerInfo(userDetails.getUsername(), updateDto);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "수정된 정보가 저장되었습니다!");
        return ResponseEntity.ok().body(response);
    }

    // //추천 페이지
    // @GetMapping("/codyrecommend")
    // public String codyrecommend(Authentication authentication, Model model){

    //     UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    //     model.addAttribute("username", userDetails.getUsername());
        
    //     return "codyrecommend";
    // }
    
    // //내 옷장
    // @GetMapping("/mycloset/{username}")
    // public String mycloset(Authentication authentication, Model model){

    //     UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    //     model.addAttribute("username", userDetails.getUsername());
        
    //     return "mycloset";
    // }

    // //옷장 상세
    // @GetMapping("/myclosetdetail/{username}")
    // public String myclosetdetail(Authentication authentication, Model model) {
    //     if(authentication != null){
    //         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    //         model.addAttribute("username", userDetails.getUsername());
    //     }
    //     return "myclosetdetail";
    // }

    // //옷 업로드
    // @GetMapping("/myclosetupload/{username}")
    // public String myclosetupload(Authentication authentication, Model model) {
    //     if(authentication != null){
    //         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    //         model.addAttribute("username", userDetails.getUsername());
    //     }
    //     return "myclosetupload";
    // }


}
