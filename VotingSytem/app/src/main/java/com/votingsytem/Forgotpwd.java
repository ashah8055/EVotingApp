package com.votingsytem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.votingsytem.database.DatabaseHelper;
import com.votingsytem.pojo.User_POJO;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Forgotpwd extends Activity {

	/////Header
	LinearLayout back;
	TextView head;
	/////

	EditText edtx_email;
	Button save_btn;
	User_POJO user = new User_POJO();
	SharedPreferences sharedPreferences;
	ProgressDialog dialog;
	DatabaseHelper databaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgotpwd);
		databaseHelper = new DatabaseHelper(getApplicationContext());
		databaseHelper.openDataBase();
		/////Header
		head = (TextView) findViewById(R.id.head);
		head.setText("Forgot Password");
		back = (LinearLayout) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		/////


		sharedPreferences = getSharedPreferences("VotingSystem", MODE_PRIVATE);

		edtx_email = (EditText) findViewById(R.id.edtx_email);
		save_btn = (Button) findViewById(R.id.save_btn);
		save_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if (isValidEmail(edtx_email.getText().toString().trim())) {
					user = databaseHelper.getProfileFromEmail(edtx_email.getText().toString().trim());
					if (user != null && user.email != null && user.email.toString().trim().length() > 0) {
						new AsyncTask<Void, Void, Void>() {
							@Override
							protected void onPreExecute() {
								dialog = dialog.show(Forgotpwd.this, "Loading",
										"Please Wait...!!");
								dialog.setCancelable(false);
								super.onPreExecute();
							}

							@Override
							protected Void doInBackground(Void... voids) {
								sendEmail();
								return null;
							}

							@Override
							protected void onPostExecute(Void aVoid) {
								dialog.dismiss();
								Toast.makeText(Forgotpwd.this, "Email Sent Successfully", Toast.LENGTH_SHORT).show();
								super.onPostExecute(aVoid);
							}
						}.execute();
					} else {
						Toast.makeText(Forgotpwd.this, "User is not present in our database.", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(Forgotpwd.this, "Enter proper email id.", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	public final static boolean isValidEmail(CharSequence target) {
		if (TextUtils.isEmpty(target)) {
			return false;
		} else {
			return android.util.Patterns.EMAIL_ADDRESS.matcher(target)
					.matches();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forgot__password, menu);
		return true;
	}


	public void sendEmail() {
		// Recipient's email ID needs to be mentioned.
		final String user = "shahkishansn@gmail.com";//change accordingly
		final String password = "kishan0979";//change accordingly

		String to = edtx_email.getText().toString().trim();//change accordingly

		//Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, password);
					}
				});

		//Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("EVoting System");
			message.setText("Your Password is :" + this.user.password);

			//send the message
			Transport.send(message);

			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	//public void sendEmail(String email) {
	// Recipient's email ID needs to be mentioned.
        /*String finalMessage = "";
        String to = email;

        // Sender's email ID needs to be mentioned
        String from = "pinkeshkhatri@gmail.com";
        final String username = "pinkeshkhatri@gmail.com";//change accordingly
        final String password = "9574306114";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        //String host = "relay.jangosmtp.net";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Report Analysis");

            // Send the actual HTML message, as big as you like
            //message.setContent(finalMessage, "text/html");


            ////////////////////////////////////////////////////
            // first part (the html)
            MimeMultipart multipart = new MimeMultipart("related");
            *//*BodyPart messageBodyPart = new MimeBodyPart();
            //String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
            messageBodyPart.setContent(finalMessage, "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);

            // second part (the image)
            messageBodyPart = new MimeBodyPart();

            for (int i = 0; i < list.size(); i++) {
                DataSource fds = new FileDataSource(
                        list.get(0).getImage());
                messageBodyPart.setDataHandler(new DataHandler(fds));
                messageBodyPart.addHeader("Content-ID", "<" + i + ">");
            }
            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);*//*


            /// working ///
            BodyPart bodyPart = new MimeBodyPart();
            //String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
            bodyPart.setContent(finalMessage, "text/html");
            // add it
            multipart.addBodyPart(bodyPart);
            for (int i = 0; i < App.getList().size(); i++) {
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                DataSource fds = new FileDataSource(
                        App.getList().get(i).getImage());
                messageBodyPart.setDataHandler(new DataHandler(fds));
                messageBodyPart.addHeader("Content-ID", "<" + App.getList().get(i).getImage() + ">");
                // add it
                multipart.addBodyPart(messageBodyPart);
            }
            message.setContent(multipart);

            /// SINGLE IMAGE
            *//*MimeBodyPart messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(
                    list.getImage());
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.addHeader("Content-ID", "<" + list.getImage() + ">");
            // add it
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);*//*
            /// working ///

            ////////////////////////////////////////////////////

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }*/
	//}

}
