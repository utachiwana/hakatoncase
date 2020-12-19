package com.utachiwana.athena.ui.menu;

import com.utachiwana.athena.data.Post;
import com.utachiwana.athena.ui.logic.MVPView;

import java.util.List;

public interface MenuView extends MVPView {

    void showPosts(List<Post> data);
}
