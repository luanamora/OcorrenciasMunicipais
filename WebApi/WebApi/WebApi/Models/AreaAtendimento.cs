using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class AreaAtendimento
    {
        public AreaAtendimento()
        {
            Ocorrencia = new HashSet<Ocorrencia>();
            TelefoneAreaatendimento = new HashSet<TelefoneAreaatendimento>();
            UsuarioAreaatendimento = new HashSet<UsuarioAreaatendimento>();
        }

        [JsonProperty("cd_areaatendimento")]
        public int CdAreaatendimento { get; set; }
        [JsonProperty("cd_areaatuacao")]
        public int CdAreaatuacao { get; set; }
        [JsonProperty("ds_areaatendimento")]
        public string DsAreaatendimento { get; set; }
        [JsonProperty("ds_email")]
        public string DsEmail { get; set; }
        [JsonProperty("dt_cadastro")]
        public DateTime DtCadastro { get; set; }
        [JsonProperty("dt_Atualizacao")]
        public DateTime? DtAtualizacao { get; set; }


        [JsonIgnore]
        public AreaAtuacao CdAreaatuacaoNavigation { get; set; }
        [JsonIgnore]
        public ICollection<Ocorrencia> Ocorrencia { get; set; }
        [JsonIgnore]
        public ICollection<TelefoneAreaatendimento> TelefoneAreaatendimento { get; set; }
        [JsonIgnore]
        public ICollection<UsuarioAreaatendimento> UsuarioAreaatendimento { get; set; }
    }
}
