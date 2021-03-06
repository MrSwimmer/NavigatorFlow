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

import flow.Dispatcher;
import flow.Flow;
import flow.Traversal;
import flow.TraversalCallback;

public final class AuthDispatcher implements Dispatcher {

    private final Activity activity;

    public AuthDispatcher(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void dispatch(@NonNull Traversal traversal, @NonNull TraversalCallback callback) {
        Log.d("AuthDispatcher", "dispatching " + traversal);
        Object destKey = traversal.destination.top();

        ViewGroup frame = activity.findViewById(R.id.auth_container);

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
        if (destKey instanceof IntroScreen) {
            layout = R.layout.view_intro;
        } else if (destKey instanceof SignInScreen) {
            layout = R.layout.view_sign_in;
        } else if (destKey instanceof SignUpScreen) {
            layout = R.layout.view_sign_up;
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
