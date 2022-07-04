.PHONY: *
.DEFAULT_GOAL:=help

# Internal
ROOT_DIR=$(shell dirname $(realpath $(firstword $(MAKEFILE_LIST))))
VERSION=$(shell cat $(ROOT_DIR)/VERSION)
CLJ=clojure

##@ Testing

check: ## Loads all namespaces
	$(CLJ) -T:project check


test: ## Runs unit tests (alias to test-unit)
	make test-unit

test-unit: ## Runs unit tests
	$(CLJ) -T:project test :kaocha.filter/focus '[:unit]'

test-integration: ## Runs integration tests
	$(CLJ) -T:project test :kaocha.filter/focus '[:integration]'

test-all: ## Runs unit+integration tests
	$(CLJ) -T:project test

repl-test: ## Launch test repl
	clj -A:test

##@ Dependencies

deps: ## Show deps tree
	$(CLJ) -Stree

merge-deps: ## Merge dependencies on all modules from :managed-deps
	$(CLJ) -T:project merge-deps

merge-aliases: ## Merge aliases on all modules from :managed-aliases
	$(CLJ) -T:project merge-aliases

##@ Misc.

lint: ## runs linting on all modules
	$(CLJ) -T:project lint

format-check: ## Format according to linter rules
	$(CLJ) -T:project format-check

format-fix: ## Format according to linter rules
	$(CLJ) -T:project format-fix

antq: ## run antq (aka 'ancient') task on all modules
	$(CLJ) -T:project outdated

clean: ## Clean module target dirs
	$(CLJ) -T:project clean

install: ## Install all modules to local maven repo
	$(CLJ) -T:project install

version: ## Output project version
	@echo $(shell cat $(ROOT_DIR)/VERSION)

repl: ## Launch repl (no aliases)
	clj

##@ CI/CD tasks

release: ## Release jar modules & tag versions
	git config --global --add safe.directory '*'
	$(CLJ) -T:project release

##@ Helpers

.SILENT: info
info: ## Show repo information
	@echo "version:\t\t" $(shell cat $(ROOT_DIR)/VERSION)
	@echo "git-ref:\t\t" $(shell git rev-parse HEAD)

help:  ## Display this help
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m\033[0m\n"} /^[a-zA-Z_-]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)
