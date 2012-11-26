class MailOnExceptionGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.1 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    def title = "Mail On Exception Plugin" // Headline display name of the plugin
    def author = "François Terrier"
    def authorEmail = "fterrier@gmail.com"
    def description = '''\
This plugin allows one to specify an email address where all frontend exceptions will be sent.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/mail-on-exception"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
	def license = "BSD3"

    // Details of company behind the plugin (if there is one)
	def organization = [ name: "Clinton Health Access Initiative", url: "http://www.clintonhealthaccess.org/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//	def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "http://github.com/fterrier/grails-mail-on-exception" ]

    def loadAfter = ['services']

    def doWithSpring = {
		def mailOnExceptionConfig = application.config.mailOnException
		
		exceptionHandler(org.chai.exception.ExceptionHandler){
	        // this is required so that calls to super work
	    	exceptionMappings = ['java.lang.Exception': mailOnExceptionConfig.error.view]
			defaultErrorView = '/error'
			mailService = ref("mailService")
			grailsApplication = ref("grailsApplication") 
	    }
    }

}
