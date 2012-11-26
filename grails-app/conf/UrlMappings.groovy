class UrlMappings {

	def grailsApplication

	static mappings = {
		"500"(view: grailsApplication.mailOnException.config.error.view)
	}
}
