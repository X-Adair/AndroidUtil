package com.adair.util;

import android.graphics.Paint;

/**
 * 自定义View绘图使用到的相关方法
 * <p>
 * created at 2018/10/11 15:00
 *
 * @author XuShuai
 * @version v1.0
 */
public class DrawUtils {


    /**
     * 自定义View绘制文字时，为文字居中显示,需要计算文字的baseline,此方法计算文字baseline距离文字中心的的偏移量
     *
     * @param p 画笔
     * @return baseLine距离绘制矩形Y中心的偏移量, 此值为正数
     */
    public static float baseLine(Paint p) {
        Paint.FontMetrics fontMetrics = p.getFontMetrics();
        return (fontMetrics.descent - fontMetrics.ascent) / 2f - fontMetrics.descent;
    }

    /**
     * 自定义View绘制文字时，为文字居中显示,需要计算文字的baseline,此方法计算文字baseline距离文字中心的的偏移量
     * ascent:该距离是从所绘字符的baseline之上至该字符所绘制的最高点。这个距离是系统推荐。
     * descent:该距离是从所绘字符的baseline之下至该字符所绘制的最低点。这个距离是系统推荐的。
     * top:该距离是从所绘字符的baseline之上至可绘制区域的最高点。
     * bottom:该距离是从所绘字符的baseline之下至可绘制区域的最低点。
     * leading:为文本的线之间添加额外的空间，这是官方文档直译，debug时发现一般都为0.0，该值也是系统推荐的。
     * 特别注意: ascent和top都是负值，而descent和bottom:都是正值。
     * bottom和top是系统为一些极少数符号留下的空间。一般情况下我们极少使用到类似的符号，所以往往会忽略掉这些符号的存在，但是Android依然会在绘制文本的时候在文本外层留出一定的边距。
     *
     * @param p 画笔
     * @return baseLine距离绘制矩形Y中心的偏移量, 此值为正数
     */
    public static float baseLineV2(Paint p) {
        Paint.FontMetrics fontMetrics = p.getFontMetrics();
        return (fontMetrics.bottom - fontMetrics.top) / 2f - fontMetrics.bottom;
    }
}
