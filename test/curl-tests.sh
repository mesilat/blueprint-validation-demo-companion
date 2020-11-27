#!/bin/bash

curl -v -u "${TEST_JIRA_USER}:${TEST_JIRA_PASSWORD}" \
-H 'Content-Type: application/json' -d @product.json \
"${TEST_JIRA_HOME}/rest/confield-cube/1.0/product"

curl -v -u "${TEST_JIRA_USER}:${TEST_JIRA_PASSWORD}" \
"${TEST_JIRA_HOME}/rest/confield-cube/1.0/product/3080201"

curl -v -u "${TEST_JIRA_USER}:${TEST_JIRA_PASSWORD}" -X DELETE \
"${TEST_JIRA_HOME}/rest/confield-cube/1.0/product/3080201"
