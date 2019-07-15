# Install or load tidyr and dplyr

if (!require(tidyr, quietly=TRUE)) {
  install.packages("tidyr")
  library(tidyr)
}

if (!require(dplyr, quietly=TRUE)) {
  install.packages("dplyr")
  library(dplyr)
}

# set path
path <- file.path("/path/to/directory/with .xlsx file")
infile <- file.path(path, "phased_data.xlsx") # see example file in example directory
hpos <- readxl::read_excel(infile, sheet = 1, n_max = 451)
nameVector <- colnames(hpos)[5:44]

# function to split haplotypes and save data for each individual in separate txt files
splitAlleles = function(x, infile){
  y <- tolower(x)
  y <- infile %>% select(POS, RSID, REF, x)
  y  <- y %>% separate(x, c("mat","pat"), remove = F)
  outPath <- file.path(path, "patientData", paste(x, "data.txt", sep = '.'))
  write.table(y, outPath, quote = F, sep = ',', row.names = F)
  return(y)
}

lapply(nameVector, splitAlleles, hpos)