package com.karikari.goodfilter.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;

import com.karikari.goodfilter.R;
import com.karikari.goodfilter.model.SelectableItem;

import java.util.ArrayList;
import java.util.List;


public class SingleADP extends RecyclerView.Adapter<SingleADP.ViewHolder> {

    private static final String TAG = SingleADP.class.getSimpleName();

    private Context context;
    private List<SelectableItem> selectableItems = new ArrayList<>();
    private OnItemSelectedListener listener;
    private int active;
    private int selected;
    private int orientation;
    private int text_color;
    private int text_color_selected;
    private Typeface typeface;
    private float text_size;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public SingleADP(Context context, List<SelectableItem> itemList,
                     OnItemSelectedListener listener,
                     int active,
                     int selected,
                     int orientation,
                     int text_color,
                     int text_color_selected,
                     Typeface typeface,
                     float text_size) {
        this.context = context;
        this.active = active;
        this.selected = selected;
        this.listener = listener;
        this.orientation = orientation;
        this.text_color = text_color;
        this.text_color_selected = text_color_selected;
        this.typeface = typeface;
        this.text_size = text_size;
        this.selectableItems = itemList;

      /*  for (Item item : itemList){
            selectableItems.add(new SelectableItem(item, false));
        }*/
    }

    @Override
    public SingleADP.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (orientation == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_horizontal, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        }
        return new SingleADP.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SingleADP.ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return selectableItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    private SelectableItem getItem(int position) {
        if (this.selectableItems == null || this.selectableItems.get(position) == null) {
            return null;
        }
        return this.selectableItems.get(position);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mText;
        private RelativeLayout mLayout;
        private ImageButton mIcon;

        ViewHolder(View view) {
            super(view);
            mText = view.findViewById(R.id.text);
            mLayout = view.findViewById(R.id.item_layout);
            mIcon = view.findViewById(R.id.icon);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            SelectableItem selectableItem = getItem(getAdapterPosition());
            setChanges(selectableItem);
        }

        void bindView(int position) {
            SelectableItem selectableItem = getItem(position);
            if (selectableItem != null) {
                if (!selectableItem.isSelected()) {
                    mText.setText(selectableItem.getText());
                    mText.setTextSize(TypedValue.COMPLEX_UNIT_PX, text_size);
                    mText.setTextColor(text_color);
                    if (selectableItem.getIcon() != -1) {
                        mIcon.setVisibility(View.VISIBLE);
                        mIcon.setImageResource(selectableItem.getIcon());
                        mIcon.setColorFilter(text_color);
                    }else{
                        mIcon.setImageResource(0);
                    }
                    mLayout.setBackgroundResource(active);

                } else {
                    mText.setText(selectableItem.getText());
                    mText.setTextSize(TypedValue.COMPLEX_UNIT_PX, text_size);
                    mText.setTextColor(text_color_selected);
                    if (selectableItem.getIcon() != -1) {
                        mIcon.setVisibility(View.VISIBLE);
                        mIcon.setColorFilter(text_color_selected);
                    }else{
                        mIcon.setImageResource(0);
                    }

                    mLayout.setBackgroundResource(selected);
                }

                if (typeface != null) {
                    mText.setTypeface(typeface);
                }
            }
        }

        private void setChanges(SelectableItem selectableItem) {
            for (SelectableItem item : selectableItems) {
                if (!TextUtils.equals(item.getText().trim(), selectableItem.getText().trim()) && item.isSelected()) {
                    item.setSelected(false);
                } else if (TextUtils.equals(item.getText().trim(), selectableItem.getText().trim()) && !item.isSelected()) {
                    item.setSelected(true);
                }
            }
            notifyDataSetChanged();
            listener.onItemSelected(selectableItem);
        }

    }


    public interface OnItemSelectedListener {
        void onItemSelected(SelectableItem item);
    }

}
