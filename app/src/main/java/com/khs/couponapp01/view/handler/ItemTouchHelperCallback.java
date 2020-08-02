package com.khs.couponapp01.view.handler;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private static final String TAG = ItemTouchHelperCallback.class.getSimpleName();
    private ItemTouchEvent listener;

    public void addCouponTouchEvent(ItemTouchEvent listener){
        this.listener = listener;
    }

    public interface ItemTouchEvent{
        boolean onMove(int from_position, int to_position);
        void onSwiped(int position);
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int drag_flags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;       // 상하.
        int swipe_flags = ItemTouchHelper.START|ItemTouchHelper.END;    // 처음과 끝.
        return makeMovementFlags(drag_flags,swipe_flags);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        Log.d(TAG,"onMove()");
        return listener.onMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder.getAdapterPosition());
    }

}
