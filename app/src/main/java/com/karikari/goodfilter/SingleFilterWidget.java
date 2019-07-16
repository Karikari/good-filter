package com.karikari.goodfilter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karikari.goodfilter.adapters.SingleADP;
import com.karikari.goodfilter.model.Item;
import com.karikari.goodfilter.model.SelectableItem;

import java.util.ArrayList;
import java.util.List;

public class SingleFilterWidget extends LinearLayout implements SingleADP.OnItemSelectedListener {

    public static final String TAG = SingleFilterWidget.class.getSimpleName();
    private Context context;

    private FilterChangeListener listener;
    private ItemFilterChangeListener listener1;
    private int orientation = 0;
    private float textSize = 0;
    private int background_active;
    private int background_selected;
    private int text_color;
    private int text_color_selected;
    private Typeface typeface;
    private String selected_valued;
    private RecyclerView mRecyclerView;

    private String font_type;

    public SingleFilterWidget(Context context) {
        super(context);
        initView(context);
    }

    public SingleFilterWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
        initView(context);

    }

    public SingleFilterWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs);
        initView(context);
    }


    private void initAttributes(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SingleFilterWidget, 0, 0);
        try {
            this.orientation = typedArray.getInt(R.styleable.SingleFilterWidget_orientation, 0);
            this.textSize = typedArray.getDimensionPixelSize(R.styleable.SingleFilterWidget_textSize, 18);
            this.background_active = typedArray.getInt(R.styleable.SingleFilterWidget_active_background, -1);
            this.background_selected = typedArray.getInt(R.styleable.SingleFilterWidget_selected_background, -1);
            this.text_color = typedArray.getColor(R.styleable.SingleFilterWidget_text_color, getResources().getColor(R.color.black_olive));
            this.text_color_selected = typedArray.getColor(R.styleable.SingleFilterWidget_selected_text_color, getResources().getColor(R.color.colorPrimaryDark));
            this.font_type = typedArray.getString(R.styleable.SingleFilterWidget_font_family);


            if (this.background_active == -1) {
                this.background_active = R.drawable.pill_bg;
            }

            if (this.background_selected == -1) {
                this.background_selected = R.drawable.pill_selected_bg;
            }
            if (!TextUtils.isEmpty(font_type)) {
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + font_type + ".ttf");
            }

        } finally {
            typedArray.recycle();
        }
    }

    private void initView(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.pills_layout_horizontal, this);
        mRecyclerView = view.findViewById(R.id.single_recycler);
    }

    private void setItemsToRecycerler(List<SelectableItem> list) {
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager mLayoutManager;

        if (this.orientation == 1) {
            mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        } else {
            mLayoutManager = new LinearLayoutManager(context);
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SingleADP(context, list, this,
                background_active,
                background_selected,
                orientation,
                text_color,
                text_color_selected,
                typeface,
                textSize);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemSelected(SelectableItem item) {
        Log.d(TAG, "Item : " + item.getText());
        if (listener != null) {
            listener.onFiltered(item.getText());
        }
        if (listener1 != null) {
            listener1.onFiltered(item);
        }
    }


    public void setStringValues(List<String> list) {
        List<SelectableItem> items = new ArrayList<>();
        for (String s : list) {
            if (!TextUtils.isEmpty(selected_valued) && TextUtils.equals(s, selected_valued)) {
                items.add(new SelectableItem(new Item(s), true));
            } else {
                items.add(new SelectableItem(new Item(s), false));
            }
        }
        setItemsToRecycerler(items);
    }

    public void setItemValues(List<Item> itemList) {
        List<SelectableItem> items = new ArrayList<>();
        for (Item s : itemList) {
            if (!TextUtils.isEmpty(selected_valued) && TextUtils.equals(s.getText(), selected_valued)) {
                items.add(new SelectableItem(s, true));
            } else {
                items.add(new SelectableItem(s, false));
            }
        }
        setItemsToRecycerler(items);
    }

    public String getDefaultValue() {
        return selected_valued;
    }

    public void setDefaultValue(String selected_valued) {
        this.selected_valued = selected_valued;
    }

    public void setOnFilterChangeListener(FilterChangeListener listener) {
        this.listener = listener;
    }

    public void setOnItemFilterChangeListener(ItemFilterChangeListener listener1) {
        this.listener1 = listener1;
    }

    public interface FilterChangeListener {
        void onFiltered(String v);
    }

    public interface ItemFilterChangeListener {
        void onFiltered(Item item);
    }

}
