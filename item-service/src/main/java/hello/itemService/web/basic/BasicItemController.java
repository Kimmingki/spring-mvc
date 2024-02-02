package hello.itemService.web.basic;

import hello.itemService.domain.item.Item;
import hello.itemService.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basic/items")
public class BasicItemController {

    private final ItemRepository itemRepository;

    /**
     * 상품 목록 조회
     * @param model
     * @return
     */
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }

    /**
     * 상품 상세 조회
     * @param itemId 상품 ID
     * @param model
     * @return
     */
    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/item";
    }

    /**
     * 상품 등록 폼
     * @return
     */
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam(value = "itemName") String itemName,
                       @RequestParam(value = "price") Integer price,
                       @RequestParam(value = "quantity") Integer quantity,
                       Model model) {

        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute(value = "item") Item item) {
        itemRepository.save(item);

//        model.addAttribute("item", item);     자동 추가, 생략 가능

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);

//        model.addAttribute("item", item);     자동 추가, 생략 가능

        return "basic/item";
    }

    /**
     * 상품 등록 로직
     * @param item 상품 정보
     * @return
     */
    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);

        return "basic/item";
    }

    /**
     * 상품 수정 폼
     * @param itemId 상품 ID
     * @param model
     * @return
     */
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable(value = "itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }

    /**
     * 상품 수정 로직
     * @param itemId 상품 ID
     * @param item 상품 수정 정보
     * @return
     */
    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable(value = "itemId") Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
