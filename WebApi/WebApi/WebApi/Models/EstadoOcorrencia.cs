using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class EstadoOcorrencia
    {
        public EstadoOcorrencia()
        {
            Ocorrencia = new HashSet<Ocorrencia>();
        }

        [JsonProperty("cd_estadoocorrencia")]
        public int CdEstadoocorrencia { get; set; }
        [JsonProperty("ds_estadoocorrencia")]
        public string DsEstadoocorrencia { get; set; }
        [JsonProperty("dt_cadastro")]
        public DateTime DtCadastro { get; set; }
        [JsonProperty("dt_atualizacao")]
        public DateTime? DtAtualizacao { get; set; }

        [JsonIgnore]
        public ICollection<Ocorrencia> Ocorrencia { get; set; }
    }
}
