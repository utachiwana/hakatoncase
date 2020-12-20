package com.utachiwana.athena.ui.logic;

public interface FreeTimeClickedListener {
    void onClick(int pos);
    void allItemsRemoved();
    void addFreeTime(String time);
    void loadFreeTimes();
}
