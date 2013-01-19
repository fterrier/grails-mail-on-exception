log4j = {
	error 'org.codehaus.groovy.grails',
	      'org.springframework',
	      'org.hibernate',
	      'net.sf.ehcache.hibernate'
}

mailOnException.enabled = true // enabled by default
mailOnException.email.to = "to@example.com"
mailOnException.email.from = "from@example.com"
mailOnException.error.view = "/error"