using Newtonsoft.Json;
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
        [JsonProperty("cd_prioridade")]
        public int CdPrioridade { get; set; }
        [JsonProperty("ds_prioridade")]
        public string DsPrioridade { get; set; }
        [JsonProperty("nr_prioridade")]
        public int NrPrioridade { get; set; }
        [JsonProperty("dt_cadastro")]
        public DateTime DtCadastro { get; set; }
        [JsonProperty("dt_atualizacao")]
        public DateTime? DtAtualizacao { get; set; }



        [JsonIgnore]
        public ICollection<Ocorrencia> Ocorrencia { get; set; }
    }
}
