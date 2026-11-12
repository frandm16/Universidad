# Número de filas de un Data Frame
nrow(dfMec)

# Número de columnas de un Data frame
length(dfMec)

#Ordenar un vector
sort(dfMec$notaFinal)

# Ordenar un data frame
dfMec %>% 
  arrange(desc(notaFinal))

# Añadir una nueva columna
dfMec %>% 
  mutate(masEdad=edad+3)

# Sumarizar todo el data frame
dfMec %>% summarise(mediaEdad=mean(edad), mediaNota=mean(notaFinal))

# Agrupar y sumarizar. El uso habitual.
dfMec %>% 
  group_by(edad) %>% 
  summarise(n=n(), mediaNota=mean(notaFinal))

# Agrupar y modificar. Uso avanzado
dfMec %>% 
  group_by(edad) %>% 
  mutate(nota2=notaFinal / mean(notaFinal)) %>% 
  select(edad, notaFinal, nota2) %>% 
  ungroup

# Agrupar por dos variables. No olvidar ungroup!  
dfMec %>% 
  group_by(edad, sexo) %>% 
  summarise(n=n(), mediaNota=mean(notaFinal)) %>% 
  ungroup


