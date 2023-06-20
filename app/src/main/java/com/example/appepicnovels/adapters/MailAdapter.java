package com.example.appepicnovels.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.content.Context;

public class MailAdapter {
    public static final Intent sendMail(View view, String email) {
        String recipientEmails = email;
        String subject = "Example Email";
        String body = "This is the email body.";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, recipientEmails);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);

        return intent;
    }
}
