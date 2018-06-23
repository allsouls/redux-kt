package com.allsouls.redux.utils

import android.support.v7.app.AppCompatActivity
import com.allsouls.redux.Store
import io.reactivex.Observable

/**
 * Connects an AppCompatActivity to updates from the store.
 */
fun <S> AppCompatActivity.connect(store: Store<S>): Observable<S> =
        with(RxActivity.lifecycle(this)) {
            filter { lifecycleEvent -> lifecycleEvent == RxActivity.ON_START }
                    .takeUntil(filter { lifecycleEvent -> lifecycleEvent == RxActivity.ON_DESTROY })
                    .concatMap { store.updates }
        }