grails.project.work.dir = 'target'
grails.project.source.level = 1.6

grails.project.dependency.resolution = {

	inherits 'global'
	log 'warn'

	repositories {
		grailsCentral()
	}

	plugins {
		build(':release:2.1.0', ':rest-client-builder:1.0.2') {
			export = false
		}

		compile ":mail:1.0.1"
	}
}
