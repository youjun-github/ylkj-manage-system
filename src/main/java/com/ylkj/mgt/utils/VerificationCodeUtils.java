package com.ylkj.mgt.utils;

import com.ylkj.mgt.model.MobileVerificationCode;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 生成验证码
 * @author youjun
 */
public class VerificationCodeUtils {
    public static final long VER_CODE_VALIDITY = 3 * 60;
    public static Map<String, MobileVerificationCode> verCodeMap = new ConcurrentHashMap<>();
    private static Random random = new Random();
    private static String IMG_DEFAULT_TYPE = "jpg";//默认图片格式
    private static int IMG_DEFAULT_WIDTH = 300;//图片默认宽带
    private static int IMG_DEFAULT_HEIGHT = 90;//图片默认高度

    public static byte[] drawImage(String code) {
        return drawImage(code, code.length());
    }

    public static byte[] drawImage(String code, int length) {
        return drawImage(code, length, false);
    }

    public static byte[] drawImage(String code, int length, int ilc) {
        return drawImage(code, length, ilc, -1);
    }

    public static byte[] drawImage(String code, int length, float rawp) {
        return drawImage(code, length, -1, rawp);
    }

    public static byte[] drawImage(String code, int length, boolean shear) {
        return drawImage(code, length, -1, -1, shear, IMG_DEFAULT_TYPE);
    }

    public static byte[] drawImage(String code, int length, int ilc, float rawp) {
        return drawImage(code, length, ilc, rawp, false);
    }

    public static byte[] drawImage(String code, int length, int ilc, float rawp, boolean shear) {
        return drawImage(code, length, ilc, rawp, shear, IMG_DEFAULT_TYPE);
    }

    public static byte[] drawImage(String code, int length, int ilc, float rawp, boolean shear, String ImageType) {
        return drawImage(IMG_DEFAULT_WIDTH, IMG_DEFAULT_HEIGHT, code, length, ilc, rawp, shear, ImageType);
    }

    /**
     * @param width
     * @param height
     * @param code
     * @param length
     * @param ilc       干扰线数量 只有大于0才有效,默认不画干扰线
     * @param rawp      干扰点占屏幕比率 大于0小与1,默认不画干扰点
     * @param shear
     * @param ImageType
     * @return
     * @Title: drawImage
     * @Description: 生成验证码
     * @return byte[]
     * @author youjun
     * @date 2017年10月24日 下午3:04:44
     */
    public static byte[] drawImage(int width, int height, String code, int length, int ilc, float rawp, boolean shear
            , String ImageType) {
        //生成画布
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //生成画笔
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		graphics.setColor(Color.GRAY);//设置边框颜色
        graphics.fillRect(0, 0, width + 1, height + 1);//生成外面的矩形
        Color c = null;
        if (shear) {
            graphics.setColor(c = getRandomColor(200, 250));//设置背景颜色
            graphics.fillRect(1, 1, width, height);//绘制里面的矩形
        } else {
            graphics.setColor(c = Color.WHITE);//设置背景颜色
            graphics.fillRect(1, 1, width - 2, height - 2);//绘制里面的矩形
        }

        //绘制干扰线
        if (ilc > 0) interferingLine(graphics, width, height, ilc, false);
        //绘制干扰点
        if (rawp > 0 && rawp < 1) interferingPoint(image, width, height, rawp);
        if (shear) shear(graphics, width - 2, height, c);
        //写码内容
        writeVerificationContent(graphics, width, height, code, length);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        graphics.dispose();
        try {
            ImageIO.write(image, ImageType, output);
        } catch (Exception e) {
        } finally {
            try {
                output.close();
            } catch (IOException e) {
            }
        }
        return output.toByteArray();
    }

    /**
     * @param graphics
     * @param width
     * @param height
     * @param count     干扰线数量
     * @param sameColor 干扰线颜色是否相同
     * @Title: interferingLine
     * @Description: 干扰线
     * @return void
     * @author youjun
     * @date 2017年10月24日 上午11:08:13
     */
    private static void interferingLine(Graphics graphics, int width, int height, int count, boolean sameColor) {
        if (sameColor) {
            graphics.setColor(getRandomColor(160, 200));
        }
        for (int i = 0; i < count; i++) {
            //设置线条颜色
            if (!sameColor) {
                graphics.setColor(getRandomColor(160, 200));
            }
            //设置线条位置
            int x1 = random.nextInt(width - 1);
            int y1 = random.nextInt(height - 1);
            int x2 = random.nextInt(6) + 1;
            int y2 = random.nextInt(12) + 1;
            graphics.drawLine(x1, y1, x2 + x1 + 40, y1 + y2 + 20);
        }
    }

    /**
     * @param image
     * @param width
     * @param height
     * @param rate
     * @Title: interferingPoint
     * @Description: 添加干扰点
     * @return void
     * @author youjun
     * @date 2017年10月24日 上午11:21:03
     */
    private static void interferingPoint(BufferedImage image, int width, int height, float rate) {
        int area = (int) (rate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }
    }

    /**
     * @param graphics
     * @param width
     * @param height
     * @param code
     * @param length
     * @Title: writeVerificationContent
     * @Description: 写二维码内容
     * @return void
     * @author youjun
     * @date 2017年10月26日 下午5:50:30
     */
    private static void writeVerificationContent(Graphics2D graphics, int width, int height, String code, int length) {
        graphics.setColor(getRandomColor(100, 160));
        int fontSize = height - 2;
        Font font = new Font("宋体", Font.ITALIC, fontSize);
        graphics.setFont(font);
        char[] chars = code.toCharArray();
        for (int i = 0; i < length; i++) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * random.nextDouble() * (random.nextBoolean() ? 1 : -1),
                    (width / length) * i + fontSize / 2, height / 2);
            graphics.setTransform(affine);
            graphics.drawChars(chars, i, 1, ((width - 10) / length) * i + 5, height / 2 + fontSize / 2 - 5);
        }
    }

    private static Color getRandomColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }

    }

    public static void main(String[] args) throws IOException {
        byte[] bs = drawImage(UUID.randomUUID().toString(), 4);
        System.out.println(Base64Utils.encodeToString(bs));
        FileOutputStream out = new FileOutputStream("e://a.jpg");
        out.write(bs);
        out.flush();
        out.close();
    }
}
