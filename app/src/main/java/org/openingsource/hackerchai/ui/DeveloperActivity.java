package org.openingsource.hackerchai.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItemOnClickAction;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import org.openingsource.hackerchai.R;

import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.licenses.License;
import de.psdev.licensesdialog.licenses.MITLicense;
import de.psdev.licensesdialog.model.Notice;

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
                .setOnClickAction(ConvenienceBuilder.createWebViewDialogOnClickAction(c, c.getResources().getString(R.string.dialog_release), c.getResources().getString(R.string.release), true, false))
                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.string_license)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .sizeDp(18))
                .setOnClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {
                        final String name = c.getResources().getString(R.string.license_dialog);
                        final String url = c.getResources().getString(R.string.app_repo);
                        final String copyright ="Copyright 2018 Hackerchai <org.openingsource.hackerchai.com@gmail.com>";
                        final License license = new MITLicense();
                        final Notice notice = new Notice(name, url, copyright, license);
                        new LicensesDialog.Builder(DeveloperActivity.this)
                                .setNotices(notice)
                                .build()
                                .show();

                    }
                })
                .build());

        MaterialAboutCard.Builder authorCardBuilder = new MaterialAboutCard.Builder();
        authorCardBuilder.title(R.string.string_developer);

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.author_name)
                .subText(R.string.author_email)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_account)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse(c.getResources().getString(R.string.author_github))))
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.contributor_6)
                .subText(R.string.contributor_6_email)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_account)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse(c.getResources().getString(R.string.contributor_6_link))))
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.contributor_1)
                .subText(R.string.contributor_1_email)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_account)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse(c.getResources().getString(R.string.contributor_1_link))))
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.contributor_2)
                .subText(R.string.contributor_2_email)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_account)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse(c.getResources().getString(R.string.contributor_2_link))))
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.contributor_3)
                .subText(R.string.contributor_3_email)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_account)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse(c.getResources().getString(R.string.contributor_3_link))))
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.contributor_4)
                .subText(R.string.contributor_4_email)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_account)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse(c.getResources().getString(R.string.contributor_4_link))))
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.contributor_5)
                .subText(R.string.contributor_5_email)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_account)
                        .sizeDp(18))
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.string_fork_on_github)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_github_circle)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse(c.getResources().getString(R.string.app_repo))))
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
