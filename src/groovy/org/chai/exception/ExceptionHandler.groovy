package org.chai.exception

/*
 * Copyright (c) 2012, Clinton Health Access Initiative.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Clinton Health Access Initiative nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL CLINTON HEALTH ACCESS INITIATIVE BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import grails.util.GrailsUtil

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.codehaus.groovy.grails.web.errors.GrailsExceptionResolver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.ModelAndView

class ExceptionHandler extends GrailsExceptionResolver {

	private static final Logger log = LoggerFactory.getLogger(ExceptionHandler)

	def mailService
	def grailsApplication

	ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
		
		if(grailsApplication.config.mailOnException.enabled)
		{
			if (log.debugEnabled) log.debug("resolveException(request=${request}, response=${response}, handler=${handler}, exception=${exception})")
	
			def adminEmail = grailsApplication.config.mailOnException.email.to
			def fromEmail = grailsApplication.config.mailOnException.email.from
			def g = grailsApplication.mainContext.getBean('org.codehaus.groovy.grails.plugins.web.taglib.RenderTagLib')
			g.metaClass.prettyPrintStatus = { return '' }
	
			try {
				mailService.sendMail {
					multipart true
					to adminEmail
					from fromEmail
					subject "Unhandled exception in the ${GrailsUtil.environment} environment"
					html g.renderException(exception: exception)
				}
			} catch (Exception e) {
				if (log.errorEnabled) log.error("could not send email after exception", e)
			}
		}
		
		return super.resolveException(request, response, handler, exception)
	}
}
