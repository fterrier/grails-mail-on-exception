import org.chai.exception.ExceptionHandler

class MailOnExceptionGrailsPlugin {
	def version = "0.1"
	def grailsVersion = "2.0 > *"
	def title = "Mail On Exception Plugin"
	def author = "François Terrier"
	def authorEmail = "fterrier@gmail.com"
	def description = 'Allows one to specify an email address where all frontend exceptions will be sent.'
	def documentation = "http://grails.org/plugin/mail-on-exception"

	def license = "BSD3"
	def organization = [ name: "Clinton Health Access Initiative", url: "http://www.clintonhealthaccess.org/" ]
	def issueManagement = [system: 'Github', url: 'https://github.com/fterrier/grails-mail-on-exception/issues']
	def scm = [url: "http://github.com/fterrier/grails-mail-on-exception"]

	def loadAfter = ['services']

	def doWithSpring = {
		def mailOnExceptionConfig = application.config.mailOnException

		exceptionHandler(ExceptionHandler) {
			// this is required so that calls to super work
			exceptionMappings = ['java.lang.Exception': mailOnExceptionConfig.error.view]
			defaultErrorView = '/error'
			mailService = ref("mailService")
			grailsApplication = ref("grailsApplication")
		}
	}
}
