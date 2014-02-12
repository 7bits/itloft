#!/bin/sh

mkdir -p db-backups
mysqldump --host=localhost --user=itloftuser --password=itloftpassword itloftdb | gzip > ./db-backups/itloft.`date +"%Y-%m-%d"`.sql.gz
