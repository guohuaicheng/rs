package com.qiqi.rs.admin.utils.controller;

import com.google.zxing.WriterException;
import com.qiqi.core.model.Result;
import com.qiqi.core.runtime.threadlocal.ClientInfoThreadLocalHolder;
import com.qiqi.core.utils.QrcodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/utils")
public class UtilsController {
    /**
     * 将Url转为二维码图片
     *
     * @param response
     * @param qrInfo
     * @return
     * @throws IOException
     * @throws WriterException
     */
    @RequestMapping(value = "/qrcode")
    public Result<String> createQr(HttpServletResponse response,
                                   @RequestParam(required = true) String qrInfo,
                                   @RequestParam(defaultValue = "200") int size) throws IOException, WriterException {
        response.setHeader("ByCache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // String qrUrl = SystemParamCache.get(SystemParamEnum.URL.code()) + htmlPath;

        BufferedImage image = QrcodeUtil.getEncodeQRCODE(qrInfo, size);
        // 返回Base64图片
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", out);
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return new Result<String>(encoder.encode(out.toByteArray()));
    }

    /**
     * 获取客户端ip
     */
    @RequestMapping(value = "/ip")
    public Result<String> createQr() {
        return new Result<String>(ClientInfoThreadLocalHolder.get().getIp());
    }
}
