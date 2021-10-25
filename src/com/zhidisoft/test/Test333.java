package com.zhidisoft.test;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;

import java.awt.*;

public class Test333 {
    public static void main(String[] args) {
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.RED.getRGB());
        // 设置背景色（灰色）
        config.setBackColor(Color.YELLOW.getRGB());

        // 生成二维码到文件，也可以到流
        QrCodeUtil.generate("http://www.baidu.com/", config, FileUtil.file("D:/stp/lh.jpg"));
        String decode = QrCodeUtil.decode(FileUtil.file("D:/lh.jpg"));
        System.out.println(decode);
    }
}
