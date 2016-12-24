package com.mostafa_anter.watchface;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

public class WearableDataService extends WearableListenerService implements
        DataApi.DataListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient;
    protected static final String DATA_PATH = "/sunshine-watchface/data";

    @Override
    public void onCreate() {
        super.onCreate();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Wearable.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onDestroy() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            Wearable.DataApi.removeListener(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }    }

    @Override
    public void onConnected(@Nullable Bundle connectionHint) {
        Log.d(SunshineWatchFaceService.TAG, "onConnected: " + connectionHint);
        Wearable.DataApi.addListener(mGoogleApiClient, this);

//        Wearable.NodeApi.getConnectedNodes(mGoogleApiClient)
//                .setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
//                    @Override
//                    public void onResult(@NonNull NodeApi.GetConnectedNodesResult getConnectedNodesResult) {
//                        PutDataMapRequest putDataMapReq = PutDataMapRequest.create(DATA_PATH);
//                        putDataMapReq.getDataMap().putString("GET_DATA", "dummy");
//                        putDataMapReq.getDataMap().putLong("ts", System.currentTimeMillis());
//                        PutDataRequest putDataReq = putDataMapReq.asPutDataRequest();
//
//                        Log.d(SunshineWatchFaceService.TAG, "sending to phone: " + putDataMapReq.getDataMap().toString());
//                        Wearable.DataApi.putDataItem(mGoogleApiClient,putDataReq)
//                                .setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
//                                    @Override
//                                    public void onResult(@NonNull DataApi.DataItemResult dataItemResult) {
//                                        if (dataItemResult.getStatus().isSuccess()){
//                                            Log.d(SunshineWatchFaceService.TAG, "Success sending data item to phone!");
//                                        } else {
//                                            Log.d(SunshineWatchFaceService.TAG, "Failure sending data item to phone!");
//                                        }
//                                    }
//                                });
//                    }
//                });
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.d(SunshineWatchFaceService.TAG, "onConnectionSuspended: " + cause);
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        Log.d(SunshineWatchFaceService.TAG, "Reveived!!:" + dataEventBuffer);

        for (DataEvent dataEvent : dataEventBuffer) {
            if (dataEvent.getType() != DataEvent.TYPE_CHANGED) {
                continue;
            }

            DataItem dataItem = dataEvent.getDataItem();
            if (!dataItem.getUri().getPath().equals(
                    DATA_PATH)) {
                continue;
            }

            DataMapItem dataMapItem = DataMapItem.fromDataItem(dataItem);
            DataMap dataMap = dataMapItem.getDataMap();
            Log.d(SunshineWatchFaceService.TAG, "Data item updated:" + dataMap);
            SunshineWatchFaceService.HIGH_TEMP_DATA = dataMap.getString("HIGH");
            SunshineWatchFaceService.LOW_TEMP_DATA = dataMap.getString("LOW");
            SunshineWatchFaceService.WEATHER_ID_DATA = dataMap.getInt("WEATHER_ID_DATA");
            Log.d(SunshineWatchFaceService.TAG, "new weather id:" + SunshineWatchFaceService.WEATHER_ID_DATA);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(SunshineWatchFaceService.TAG, "onConnectionFailed: " + connectionResult);
    }
}