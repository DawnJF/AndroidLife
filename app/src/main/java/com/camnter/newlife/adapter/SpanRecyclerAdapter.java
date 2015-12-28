package com.camnter.newlife.adapter;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.BlurMaskFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Parcel;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.MaskFilterSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TabStopSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.camnter.easyrecyclerview.adapter.EasyRecyclerViewAdapter;
import com.camnter.easyrecyclerview.holder.EasyRecyclerViewHolder;
import com.camnter.newlife.R;
import com.camnter.newlife.bean.SpanData;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Description：SpanRecyclerAdapter
 * Created by：CaMnter
 * Time：2015-12-27 13:41
 */
public class SpanRecyclerAdapter extends EasyRecyclerViewAdapter {

    private Context context;

    private static final int URL_SPAN = 1;
    private static final int UNDERLINE_SPAN = 2;
    private static final int TYPEFACE_SPAN = 3;
    private static final int TEXT_APPERARANCE_SPAN = 4;
    private static final int TAB_STOP_SPAN = 5;
    private static final int SUPERS_SCRIPT_SPAN = 6;
    private static final int SUB_SCRIPT_SPAN = 7;
    private static final int STRIKE_THROUGH_SPAN = 8;
    private static final int SCALE_X_SPAN = 9;
    private static final int STYLE_SPAN = 10;
    private static final int RELATIVE_SIZE_SPAN = 11;
    private static final int QUOTO_SPAN = 12;
    private static final int MASK_FILTER_SPAN = 13;
    private static final int LEADING_MARGIN_SPAN = 14;
    private static final int IMAGE_SPAN = 15;
    private static final int ICON_MARGIN_SPAN = 16;
    private static final int FOREGROUND_COLOR_SPAN = 17;
    private static final int DRAWABLE_MARGIN_SPAN = 18;
    private static final int BULLET_SPAN = 19;
    private static final int BACKGROUND_COLOR_SPAN = 20;
    private static final int ALIGNMENT_SPAN_STANDARD = 21;
    private static final int ABSOLUTE_SIZE_SPAN = 22;

    public SpanRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int[] getItemLayouts() {
        return new int[]{R.layout.item_span_content, R.layout.item_span_title};
    }

    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder easyRecyclerViewHolder, int i) {
        SpanData spanData = this.getItem(i);
        if (spanData == null) return;
        int itemType = this.getRecycleViewItemType(i);
        switch (itemType) {
            case SpanData.CONTENT: {
                TextView labelTV = easyRecyclerViewHolder.findViewById(R.id.span_label_tv);
                TextView contentTV = easyRecyclerViewHolder.findViewById(R.id.span_content_tv);
                if (spanData.getContent() != null) {
                    this.setSpanContent(labelTV, contentTV, spanData.getContent(), i);
                } else {
                    contentTV.setText("??????");
                    labelTV.setText("??????");
                }
                break;
            }
        }
    }

    @Override
    public int getRecycleViewItemType(int i) {
        SpanData spanData = this.getItem(i);
        return spanData.getType();
    }

    public void setSpanContent(TextView labelTV, TextView contentTV, String content, int position) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(content);
        String sub = "Save";
        int start = content.indexOf("Save");
        switch (position) {
            case URL_SPAN: {
                labelTV.setText("URLSpan");
                ssb.setSpan(new URLSpan("https://github.com/CaMnter"), start, sub.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                contentTV.setText(ssb);
                // 在单击链接时凡是有要执行的动作，都必须设置MovementMethod对象
                contentTV.setMovementMethod(LinkMovementMethod.getInstance());
                // 设置点击后的颜色，这里涉及到ClickableSpan的点击背景
                contentTV.setHighlightColor(0xff8FABCC);
                break;
            }
            case UNDERLINE_SPAN: {
                labelTV.setText("UnderlineSpan");
                ssb.setSpan(new UnderlineSpan(), start, start + sub.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                contentTV.setText(ssb);
                break;
            }
            case TYPEFACE_SPAN: {
                labelTV.setText("TypefaceSpan ( Examples include \"monospace\", \"serif\", and \"sans-serif\". )");
                ssb.setSpan(new TypefaceSpan("serif"), start, start + sub.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                contentTV.setText(ssb);
                break;
            }
            case TEXT_APPERARANCE_SPAN: {
                labelTV.setText("TextAppearanceSpan");
                ColorStateList colorStateList = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    colorStateList = this.context.getColorStateList(R.color.selector_apperarance_span);
                } else {
                    try {
                        colorStateList = ColorStateList.createFromXml(this.context.getResources(), this.context.getResources().getXml(R.color.selector_apperarance_span));
                    } catch (XmlPullParserException | IOException e) {
                        e.printStackTrace();
                    }
                }
                ssb.setSpan(new TextAppearanceSpan("serif", Typeface.BOLD_ITALIC, this.context.getResources().getDimensionPixelSize(R.dimen.text_appearance_span), colorStateList, colorStateList), start, start + sub.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                contentTV.setText(ssb);
                break;
            }
            case TAB_STOP_SPAN: {
                labelTV.setText("TabStopSpan.Standard");
                String[] subs = content.split(" ");
                ssb = new SpannableStringBuilder();
                /**
                 * TabStopSpan. Standard related to \t and \n
                 * TabStopSpan.Standard 跟 \t 和 \n 有关系
                 */
                for (String sub1 : subs) {
                    ssb.append("\t").append(sub1).append(" ");
                    ssb.append("\n");
                }
                ssb.setSpan(new TabStopSpan.Standard(126), 0, ssb.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                contentTV.setText(ssb);
                break;
            }
            case SUPERS_SCRIPT_SPAN: {
                labelTV.setText("SuperscriptSpan");
                ssb.replace(start, start + sub.length(), "Save6");
                Parcel parcel = Parcel.obtain();
                parcel.writeInt(6);
                int sixPosition = ssb.toString().indexOf("6");
                ssb.setSpan(new SuperscriptSpan(parcel), sixPosition, sixPosition + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                parcel.recycle();
                contentTV.setText(ssb);
                break;
            }
            case SUB_SCRIPT_SPAN: {
                labelTV.setText("SubscriptSpan");
                ssb.replace(start, start + sub.length(), "Save6");
                Parcel parcel = Parcel.obtain();
                parcel.writeInt(6);
                int sixPosition = ssb.toString().indexOf("6");
                ssb.setSpan(new SubscriptSpan(parcel), sixPosition, sixPosition + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                parcel.recycle();
                contentTV.setText(ssb);
                break;
            }
            case STRIKE_THROUGH_SPAN: {
                labelTV.setText("StrikethroughSpan");
                ssb.setSpan(new StrikethroughSpan(), start, start + sub.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                contentTV.setText(ssb);
                break;
            }
            case SCALE_X_SPAN: {
                labelTV.setText("ScaleXSpan");
                ssb.setSpan(new ScaleXSpan(2.0f), start, start + sub.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                contentTV.setText(ssb);
                break;
            }
            case STYLE_SPAN: {
                labelTV.setText("StyleSpan ( Typeface.NORMAL,Typeface.BOLD,Typeface.ITALIC,Typeface.BOLD_ITALIC ) ");
                ssb.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), start, start + sub.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                contentTV.setText(ssb);
                break;
            }
            case RELATIVE_SIZE_SPAN: {
                labelTV.setText("RelativeSizeSpan");
                ssb.setSpan(new RelativeSizeSpan(6.0f), start, start + sub.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                contentTV.setText(ssb);
                break;
            }
            case QUOTO_SPAN: {
                labelTV.setText("QuoteSpan");
                ssb.setSpan(new QuoteSpan(0xff000000), start, start + sub.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                contentTV.setText(ssb);
                break;
            }
            case MASK_FILTER_SPAN: {
                labelTV.setText("MaskFilterSpan ( BlurMaskFilter EmbossMaskFilter ) \n Activity: android:hardwareAccelerated=\"false\"\n ");
                MaskFilterSpan embossMaskFilterSpan = new MaskFilterSpan(new EmbossMaskFilter(new float[]{3, 3, 9}, 3.0f, 12, 16));
                ssb.setSpan(embossMaskFilterSpan, start, start + sub.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                contentTV.setText(ssb);
                String you = "you";
                int indexYou = content.indexOf(you);
                MaskFilterSpan blurMaskFilterSpan = new MaskFilterSpan(new BlurMaskFilter(3, BlurMaskFilter.Blur.OUTER));
                ssb.setSpan(blurMaskFilterSpan, indexYou, indexYou + you.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                contentTV.setText(ssb);
                break;
            }
        }
    }

}