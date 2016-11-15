package ru.menkin.ea.lec2;

import org.springframework.beans.factory.annotation.Required;

import ru.menkin.ea.lec1.service.Controller;

public class RunnerBean {
	private Controller _controller;

	@Required
	public void setController(Controller controller) {
		_controller = controller;
	}

	public Controller getController() {
		return _controller;
	}

	public void init() {
		_controller.doTheWork();
	}
}
