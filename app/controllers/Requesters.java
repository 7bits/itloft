package controllers;

import play.mvc.With;

@With(Secure.class)
public class Requesters extends CRUD {}
