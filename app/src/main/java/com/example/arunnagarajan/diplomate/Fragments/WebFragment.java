package com.example.arunnagarajan.diplomate.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.arunnagarajan.diplomate.R;


public class WebFragment extends Fragment {

WebView webview;

    public WebFragment() {

    }



    public static WebFragment newInstance(String param1, String param2) {
        WebFragment fragment = new WebFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
 View simpleView = inflater.inflate(R.layout.fragment_web, container, false);
 webview = simpleView.findViewById(R.id.webview);
 webview.loadUrl("https://www.reddit.com/r/ibo");
 WebSettings webSettings = webview.getSettings();
         webSettings.setJavaScriptEnabled(true);
        return simpleView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



}
