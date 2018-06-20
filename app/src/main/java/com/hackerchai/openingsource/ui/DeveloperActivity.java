package com.hackerchai.openingsource.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;


import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItemOnClickAction;

import com.hackerchai.openingsource.R;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

public class DeveloperActivity extends MaterialAboutActivity {
    @NonNull
    @Override
    protected MaterialAboutList getMaterialAboutList(final Context c) {
        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();


        appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text(R.string.app_name)
                .desc(R.string.author_name)
                .icon(R.mipmap.ic_launcher)
                .build());

        appCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_information_outline)
                        .sizeDp(18),
                c.getResources().getString(R.string.string_version),
                false));

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.string_changelog)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_history)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebViewDialogOnClickAction(c, "Releases", "https://github.com/hackerchai/opening-source-app/release", true, false))
                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.string_license)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .sizeDp(18))
                .setOnClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {

                    }
                })
                .build());

        MaterialAboutCard.Builder authorCardBuilder = new MaterialAboutCard.Builder();
        authorCardBuilder.title(R.string.string_author);
//        authorCardBuilder.titleColor(ContextCompat.getColor(c, R.color.colorAccent));

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.author_name)
                .subText(R.string.author_nation)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_account)
                        .sizeDp(18))
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.string_fork_on_github)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_github_circle)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse(c.getResources().getString(R.string.author_github))))
                .build());

        MaterialAboutCard.Builder convenienceCardBuilder = new MaterialAboutCard.Builder();

        convenienceCardBuilder.title(R.string.app_name);

        convenienceCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_information_outline)
                        .sizeDp(18),
                c.getResources().getString(R.string.string_version),
                false));

        convenienceCardBuilder.addItem(ConvenienceBuilder.createWebsiteActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_earth)
                        .sizeDp(18),
                c.getResources().getString(R.string.string_visit_website),
                true,
                Uri.parse(c.getResources().getString(R.string.orgnization_website))));


        convenienceCardBuilder.addItem(ConvenienceBuilder.createEmailItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_email)
                        .sizeDp(18),
                c.getResources().getString(R.string.string_send_an_email),
                true,
                c.getResources().getString(R.string.author_email),
                c.getResources().getString(R.string.string_email_subject)));



        return new MaterialAboutList(appCardBuilder.build(), authorCardBuilder.build(), convenienceCardBuilder.build());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTitle(R.string.developers);
    }



    @Nullable
    @Override
    protected CharSequence getActivityTitle() {
        return null;
    }
}
