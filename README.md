Mail on Exception plugin
========================

Grails plugin that sends a message to a configured email address when a frontend exception occurs that triggers a HTTP 500 response.

This way you will be notified each time a user-visible exception occurs on your website.

This plugin uses the [mail][mail] plugin to send emails.

Installation
---

To install this plugin, run the following:

	grails install-plugin mail-on-exception

		
Configuration
---

There are 3 variables that need to be configured :

	// Tells the plugin where the mail should be sent
	mailOnException.email.to = "to@example.com"
	
	// Tells the plugin who the sender of the email is
	mailOnException.email.from = "from@example.com"
	
	// Tells the plugin what the default error view is, so the default behaviour is conserved,
	// The content of UrlMappings.groovy for "500" is ignored
	mailOnException.error.view = "/error"

You should also configure the mail plugin. Check the documentation [here][mail].

[mail]: http://grails.org/plugin/mail