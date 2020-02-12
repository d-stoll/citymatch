#!/bin/bash

mongoimport --db question-db --collection generalQuestions --file /init-data/generalQuestions.json --jsonArray