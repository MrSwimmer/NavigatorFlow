package com.navigatorflow.domain.flow.dispatcher;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.navigatorflow.R;
import com.navigatorflow.domain.screen.auth.IntroScreen;
import com.navigatorflow.domain.screen.auth.SignInScreen;
import com.navigatorflow.domain.screen.auth.SignUpScreen;
import com.navigatorflow.domain.screen.main.NewsScreen;
import com.navigatorflow.domain.screen.main.PostScreen;
import com.navigatorflow.domain.screen.main.SettingsScreen;

import flow.Dispatcher;
import flow.Flow;
import flow.Traversal;
import flow.TraversalCallback;

public final class MainDispatcher implements Dispatcher {

    private final Activity activity;

    public MainDispatcher(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void dispatch(@NonNull Traversal traversal, @NonNull TraversalCallback callback) {
        Log.d("AuthDispatcher", "dispatching " + traversal);
        Object destKey = traversal.destination.top();

        ViewGroup frame = activity.findViewById(R.id.main_container);

        if (frame.getChildCount() > 0) {
            final View currentView = frame.getChildAt(0);
            if (traversal.origin != null) {
                traversal.getState(traversal.origin.top()).save(currentView);
            }
            final Object currentKey = Flow.getKey(currentView);
            if (destKey.equals(currentKey)) {
                callback.onTraversalCompleted();
                return;
            }
            frame.removeAllViews();
        }

        @LayoutRes final int layout;
        if (destKey instanceof NewsScreen) {
            layout = R.layout.view_news;
        } else if (destKey instanceof PostScreen) {
            layout = R.layout.view_post;
        } else if (destKey instanceof SettingsScreen) {
            layout = R.layout.view_settings;
        }
        else {
            throw new AssertionError("Unrecognized screen " + destKey);
        }

        View incomingView = LayoutInflater.from(traversal.createContext(destKey, activity)) //
                .inflate(layout, frame, false);

        frame.addView(incomingView);
        traversal.getState(traversal.destination.top()).restore(incomingView);

        callback.onTraversalCompleted();
    }
}
