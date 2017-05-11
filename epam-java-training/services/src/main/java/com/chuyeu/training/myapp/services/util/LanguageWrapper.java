package com.chuyeu.training.myapp.services.util;

import org.springframework.stereotype.Component;

@Component
public class LanguageWrapper {

	private Language language;

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

}
