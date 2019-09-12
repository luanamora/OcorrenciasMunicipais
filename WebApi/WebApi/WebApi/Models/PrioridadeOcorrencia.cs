using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class PrioridadeOcorrencia
    {
        public PrioridadeOcorrencia()
        {
            Ocorrencia = new HashSet<Ocorrencia>();
        }

        public int CdPrioridade { get; set; }
        public string DsPrioridade { get; set; }
        public int NrPrioridade { get; set; }
        public DateTime DtCadastro { get; set; }
        public DateTime? DtAtualizacao { get; set; }

        public ICollection<Ocorrencia> Ocorrencia { get; set; }
    }
}
