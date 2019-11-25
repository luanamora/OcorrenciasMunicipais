using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class TipoOcorrencia
    {
        public TipoOcorrencia()
        {
            Ocorrencia = new HashSet<Ocorrencia>();
        }

        [JsonProperty("cd_tipoocorrencia")]
        public int CdTipoocorrencia { get; set; }
        [JsonProperty("ds_tipoocorrencia")]
        public string DsTipoocorrencia { get; set; }
        [JsonProperty("dt_cadastro")]
        public DateTime DtCadastro { get; set; }
        [JsonProperty("dt_atualizacao")]
        public DateTime? DtAtualizacao { get; set; }

        [JsonIgnore]
        public ICollection<Ocorrencia> Ocorrencia { get; set; }
    }
}
