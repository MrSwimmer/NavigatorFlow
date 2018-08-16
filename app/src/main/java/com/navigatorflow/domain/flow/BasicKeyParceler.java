package com.navigatorflow.domain.flow;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import flow.KeyParceler;

public final class BasicKeyParceler implements KeyParceler {
    @NonNull @Override public Parcelable toParcelable(@NonNull Object key) {
        Bundle bundle = new Bundle();
        bundle.putString("stringKey", key.toString());
        return bundle;
    }

    @NonNull @Override public Object toKey(@NonNull Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        return bundle.getString("stringKey");
    }
}
