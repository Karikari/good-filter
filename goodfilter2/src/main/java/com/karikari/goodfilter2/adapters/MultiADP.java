package com.karikari.goodfilter2.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.karikari.goodfilter2.R;
import com.karikari.goodfilter2.model.SelectableItem;

import java.util.ArrayList;
import java.util.List;


public class MultiADP extends RecyclerView.Adapter<MultiADP.ViewHolder> {

    private static final String TAG = MultiADP.class.getSimpleName();

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
    private int active_icon;
    private int selected_icon;

    private List<String> selectedItems = new ArrayList<>();


    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public MultiADP(Context context, List<SelectableItem> itemList,
                    OnItemSelectedListener listener,
                    int active,
                    int selected,
                    int orientation,
                    int text_color,
                    int text_color_selected,
                    Typeface typeface,
                    float text_size,
                    int active_icon,
                    int selected_icon) {
        this.context = context;
        this.active = active;
        this.selected = selected;
        this.listener = listener;
        this.orientation = orientation;
        this.text_color = text_color;
        this.text_color_selected = text_color_selected;
        this.typeface = typeface;
        this.text_size = text_size;
        this.active_icon = active_icon;
        this.selected_icon = selected_icon;
        this.selectableItems = itemList;

    }

    @Override
    public MultiADP.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (orientation == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_horizontal, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        }
        return new MultiADP.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MultiADP.ViewHolder holder, int position) {
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
        private ImageButton mIcon;
        private RelativeLayout mLayout;

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
            if(selectableItem!=null){
                setChanges(selectableItem);
            }
        }

        void bindView(int position) {
            SelectableItem selectableItem = getItem(position);
            if (selectableItem != null) {
                if (selectableItem.isSelected()) {
                    mText.setText(selectableItem.getText());
                    mText.setTextSize(TypedValue.COMPLEX_UNIT_PX, text_size);
                    mText.setTextColor(text_color_selected);
                    if (selected_icon != -1) {
                        mIcon.setVisibility(View.VISIBLE);
                        mIcon.setImageResource(selected_icon);
                    }else{
                        mIcon.setImageResource(0);
                    }
                    mLayout.setBackgroundResource(selected);
                } else {
                    mText.setText(selectableItem.getText());
                    mText.setTextSize(TypedValue.COMPLEX_UNIT_PX, text_size);
                    mText.setTextColor(text_color);
                    if (active_icon != -1) {
                        mIcon.setVisibility(View.VISIBLE);
                        mIcon.setImageResource(active_icon);
                    }else{
                        mIcon.setImageResource(0);
                    }
                    mLayout.setBackgroundResource(active);
                }

                if (typeface != null) {
                    mText.setTypeface(typeface);
                }
            }
        }

        private void setChanges(SelectableItem selectableItem) {
            if (selectableItem.isSelected()) {
                selectableItem.setSelected(false);
            } else {
                selectableItem.setSelected(true);
            }
            addRemove(selectableItem.isSelected(), selectableItem.getText());


        }

        private void addRemove(boolean p, String value){
            if(p){
                selectedItems.add(value);
            }else{
                selectedItems.remove(value);
            }

            notifyDataSetChanged();
            listener.onItemSelected(new Gson().toJson(selectedItems));
        }


    }


    public interface OnItemSelectedListener {
        void onItemSelected(String item);
    }

}
