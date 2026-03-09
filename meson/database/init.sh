#!/bin/bash

echo "Restaurando banco..."

psql -U postgres -d meson_restaurantes < /backup/backup.sql

echo "Banco restaurado."