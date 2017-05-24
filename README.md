# Query Runner

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.harryemartland/queryrunner/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.harryemartland/queryrunner)
[![Build Status](https://travis-ci.org/HarryEMartland/queryrunner.svg?branch=master)](https://travis-ci.org/HarryEMartland/queryrunner)

This is a framework for running investigative queries.
This can be used as a first line support tool for figuring out business configuration.

The application makes use of 'arguments' and 'queries'.
A query can have dependencies on arguments and return a value once run.
This value is then displayed on the UI.

An example query for a e-commerce website might have an argument of an item id and checks that the item still has stock left.