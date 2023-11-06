import matplotlib.pyplot as plt
import os

if __name__ == "__main__":
    if(not os.path.exists("res_plots")):
       os.mkdir("res_plots")
    for file_data in os.scandir("res"):
        with open(file_data.path,"r") as lines:
            all = []
            all_labels = []
            for line in lines:
                line = line.rstrip('\n')
                line_splited = line.split(";")
                philosopher_nr = int(line_splited[0])
                times = [int(e) for e in line_splited[1:]]
                all.append(times)
                all_labels.append(philosopher_nr)
            name = file_data.name
            number = int(name[5:].split(".")[0])
            fig, ax = plt.subplots(figsize=(number//3+7, 5))
            ax.ticklabel_format(style='plain')
            ax.set_title("Rozwiązanie "+name[0]+"\nWariant "+str(number))
            ax.set_ylabel("Czas oczekiwania w nanosekundach")
            ax.set_xlabel("Numer filozofa")
            ax.set_xticklabels(all_labels)
            ax.boxplot(all)
            fig.savefig("res_plots/"+name[:-4]+".png")
            plt.close()