package com.example.hnm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.hnm.entity.ClosetEntity;
import com.example.hnm.entity.CustomerEntity;
import com.example.hnm.config.auth.PrincipalDetails;
import com.example.hnm.dto.ClosetDto;
import com.example.hnm.service.ClosetService;
import com.example.hnm.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ClosetController {

    @Autowired
    private final ClosetService closetService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    public ClosetController(ClosetService closetService) {
        this.closetService = closetService;
    }

    @GetMapping("/myclosetupload")
    public String myClosetUploadForm(Model model, Authentication authentication) {
        if(authentication != null){
            model.addAttribute("closetDto", new ClosetDto());
            model.addAttribute("username", closetService.getUsername());
        }
        return "myclosetupload";
    }

    @PostMapping("/myclosetupload/save")
    public String saveCloset(@ModelAttribute ClosetDto closetDto, RedirectAttributes attributes, Authentication authentication, Model model) {
        if(authentication != null){
            // Authentication 객체에서 사용자 정보를 얻어와서 ClosetDto에 추가하는 로직을 여기에 구현할 수 있음
            closetService.saveCloset(closetDto);
            attributes.addFlashAttribute("message", "Closet saved successfully!");
            model.addAttribute("username", closetService.getUsername());
        }
        return "redirect:/mycloset?save=success";
    }

    @GetMapping("/mycloset")
    public String myCloset(Model model, Authentication authentication) {
        if(authentication != null){
            PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
            CustomerEntity customer = customerService.getCustomerInfo(userDetails.getUsername());
            String username = authentication.getName(); // 현재 인증된 사용자의 username
            List<ClosetEntity> closets = closetService.findByUsername(username);
            model.addAttribute("closets", closets);
            model.addAttribute("usernickname", customer.getUsernickname());
            model.addAttribute("outers", closetService.findOutersByUsername(username));
            model.addAttribute("tops", closetService.findTopsByUsername(username));
            model.addAttribute("bottoms", closetService.findBottomsByUsername(username));
            model.addAttribute("onepieces", closetService.findOnepieceByUsername(username));

            // 각 카테고리별 아이템 리스트
            List<ClosetEntity> outers = closetService.findOutersByUsername(username);
            List<ClosetEntity> tops = closetService.findTopsByUsername(username);
            List<ClosetEntity> bottoms = closetService.findBottomsByUsername(username);
            List<ClosetEntity> onepieces = closetService.findOnepieceByUsername(username);

            model.addAttribute("outers", outers);
            model.addAttribute("tops", tops);
            model.addAttribute("bottoms", bottoms);
            model.addAttribute("onepieces", onepieces);

            // 총 아이템 개수 계산
            int totalItems = outers.size() + tops.size() + bottoms.size() + onepieces.size();

            // 총 아이템 개수를 모델에 추가
            model.addAttribute("totalItems", totalItems);
        }
        return "mycloset";
    }

    @GetMapping("/myclosetdetail/{id}")
    public String myClosetDetail(@PathVariable Long id, Model model, Authentication authentication) {
        if(authentication != null){
            ClosetEntity closet = closetService.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Invalid closet Id:" + id));
            model.addAttribute("closet", closet);
            model.addAttribute("username", closetService.getUsername());
            //업로드할 때 선택된 거 가져오기
            model.addAttribute("isSpringSelected", closet.getPicseason().equals("spring"));
            model.addAttribute("isSummerSelected", closet.getPicseason().equals("summer"));
            model.addAttribute("isFallSelected", closet.getPicseason().equals("fall"));
            model.addAttribute("isWinterSelected", closet.getPicseason().equals("winter"));

            model.addAttribute("isBlazerSelected", closet.getPiccategory().equals("blazer"));
            model.addAttribute("isCardiganSelected", closet.getPiccategory().equals("cardigan"));
            model.addAttribute("isCoatSelected", closet.getPiccategory().equals("coat"));
            model.addAttribute("isFourSeasonsBlouseShirtSelected", closet.getPiccategory().equals("fourSeasonsBlouseShirt"));
            model.addAttribute("isHoodieSelected", closet.getPiccategory().equals("hoodie"));
            model.addAttribute("isJacketSelected", closet.getPiccategory().equals("jacket"));
            model.addAttribute("isJeansSelected", closet.getPiccategory().equals("jeans"));
            model.addAttribute("isLongPantsSelected", closet.getPiccategory().equals("longPants"));
            model.addAttribute("isLongSkirtSelected", closet.getPiccategory().equals("longSkirt"));
            model.addAttribute("isLongSleeveSelected", closet.getPiccategory().equals("longSleeve"));
            model.addAttribute("isMidSeasonsBlouseShirtSelected", closet.getPiccategory().equals("midSeasonsBlouseShirt"));
            model.addAttribute("isOnePieceSetSelected", closet.getPiccategory().equals("onePieceSet"));
            model.addAttribute("isParkaSelected", closet.getPiccategory().equals("parka"));
            model.addAttribute("isShortPantsSelected", closet.getPiccategory().equals("shortPants"));
            model.addAttribute("isShortSkirtSelected", closet.getPiccategory().equals("shortSkirt"));
            model.addAttribute("isShortSleeveSelected", closet.getPiccategory().equals("shortSleeve"));
            model.addAttribute("isSweaterSelected", closet.getPiccategory().equals("sweater"));
            model.addAttribute("isTrainingBottomSelected", closet.getPiccategory().equals("trainingBottom"));
            model.addAttribute("isTrainingTopSelected", closet.getPiccategory().equals("trainingTop"));
            model.addAttribute("isVestSelected", closet.getPiccategory().equals("vest"));
            model.addAttribute("isWinterOuterSelected", closet.getPiccategory().equals("winterOuter"));

            model.addAttribute("isCasualSelected", closet.getPicstyle().equals("casual"));
            model.addAttribute("isStreetSelected", closet.getPicstyle().equals("street"));
            model.addAttribute("isPreppySelected", closet.getPicstyle().equals("preppy"));
            model.addAttribute("isAmekajiSelected", closet.getPicstyle().equals("amekaji"));
            model.addAttribute("isRomanticSelected", closet.getPicstyle().equals("romantic"));
            model.addAttribute("isGirlishSelected", closet.getPicstyle().equals("girlish"));
            model.addAttribute("isBusinessSelected", closet.getPicstyle().equals("business"));
            model.addAttribute("isTeenSelected", closet.getPicstyle().equals("teen"));
            model.addAttribute("isMinimalSelected", closet.getPicstyle().equals("minimal"));
            model.addAttribute("isSportySelected", closet.getPicstyle().equals("sporty"));
        }
        return "myclosetdetail";
    }

    @PostMapping("/myclosetdetail/update/{id}")
    public String updateCloset(@PathVariable Long id, @ModelAttribute ClosetDto closetDto, RedirectAttributes attributes, Authentication authentication, Model model) {
        if(authentication != null){
            closetService.updateCloset(id, closetDto);
            attributes.addFlashAttribute("message", "Closet updated successfully!");
            model.addAttribute("username", closetService.getUsername());
        }
        return "redirect:/myclosetdetail/" + id;
    }

    @PostMapping("/myclosetdetail/delete/{id}")
    public String deleteCloset(@PathVariable Long id, RedirectAttributes attributes, Authentication authentication) {
        if(authentication != null){
            closetService.deleteCloset(id);
            attributes.addFlashAttribute("message", "Closet deleted successfully!");
        }
        return "redirect:/mycloset";
    }

    @GetMapping("/images/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        ClosetEntity closetItem = closetService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + id));
        byte[] imageBytes = closetItem.getUserpic();
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG) // 이미지 타입에 맞게 수정
                .body(imageBytes);
    }
}
