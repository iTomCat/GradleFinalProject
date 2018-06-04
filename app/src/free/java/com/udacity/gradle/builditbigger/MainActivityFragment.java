package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.tomcat.jokedisplay.JokeDispActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;


/**
 * Free version
 */

public class MainActivityFragment extends Fragment {
    private PublisherInterstitialAd mPublisherInterstitialAd = null;
    ProgressBar myProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        myProgressBar = root.findViewById(R.id.progressbar);

        // ----------------------------------------------------------------------------------------- Adver
        mPublisherInterstitialAd = new PublisherInterstitialAd(getContext());
        mPublisherInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        loadAdv();

        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                loadAdv();
        }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                tellJoke();
                loadAdv();
            }
        });

        // ----------------------------------------------------------------------------------------- Banner
        AdView mAdView = root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        // ----------------------------------------------------------------------------------------- Buton Listener
        Button button = root.findViewById(R.id.button_joke);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mPublisherInterstitialAd.isLoaded()) {
                    mPublisherInterstitialAd.show();
                } else {
                    tellJoke();
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        return root;
    }

    private void loadAdv(){
        PublisherAdRequest request = new PublisherAdRequest.Builder()
                .addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB")  // An example device ID
                .build();
        mPublisherInterstitialAd.loadAd(request);
    }

    public void tellJoke(){
        myProgressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute(this);
    }

    public void showJoke(String joke){
        Intent intent = new Intent(getContext(), JokeDispActivity.class);
        intent.putExtra(JokeDispActivity.JOKE_KEY, joke);
        getContext().startActivity(intent);

        myProgressBar.setVisibility(View.GONE);
    }


}
