package mq.adapter;
import lombok.RequiredArgsConstructor;
import mq.service.SendService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/send")
@RequiredArgsConstructor
public class SendMqController {
    private final SendService sendService;

    @GetMapping("/sendMq/{msg}")
    public String buyMember(@PathVariable String msg) {
        sendService.sendMq(msg);
        return "发送成功";
    }
}
