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
path <- file.path("ExampleData/")
infile <- file.path(path, "hla.37.phased.example-data.xlsx")
hpos <- readxl::read_excel(infile, sheet = 1, n_max = 451)

nameVector <- colnames(hpos)[5:ncol(hpos)]

# function to split haplotypes and save data for each individual in separate txt files
splitAlleles = function(x, infile){
  y <- tolower(x)
  y <- infile %>% select(POS, RSID, REF, x)
  y  <- y %>% separate(x, c("fwd","rev"), remove = F)
  outPath <- file.path(path, paste(x, "data.txt", sep = '.'))
  revDF <- file.path(path, paste(x, "data_rev.txt", sep = "."))
  write.table(y, outPath, quote = F, sep = ',', row.names = F)
  write.table(y[seq(dim(y)[1],1),], revDF, quote = F, sep = ',', row.names = F)
  return(y)
}

lapply(nameVector, splitAlleles, hpos)
